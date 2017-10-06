package io.ap1.proximityapi

import io.ap1.proximityapi.model.Event
import io.ap1.proximityapi.model.Subscriber
import io.ap1.proximityapi.model.Zones
import io.ap1.proximityapi.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutionException


@RestController
@RequestMapping("/api")
class ProximityController {

    @Autowired
    lateinit var beaconRepo: BeaconRepository

    @Autowired
    lateinit var geofenceRepo: GeofenceRepository

    @Autowired
    lateinit var networkRepo: NetworkRepository

    @Autowired
    lateinit var subscriberRepo: SubscriberRepository

    @Autowired
    lateinit var eventRepo: EventRepository

    @Autowired
    lateinit var notificationService: NotificationService

    @GetMapping("/zones")
    fun getZones(@RequestHeader("Authorization") header: String,
                 @RequestParam("lat") lat: Double,
                 @RequestParam("lng") lng: Double): ResponseEntity<ZonesResponse> {
        val zones = Zones(beaconRepo.findAll(), geofenceRepo.findAll(), networkRepo.findAll())
        return ResponseEntity.ok(ZonesResponse(zones))
    }

    @PostMapping("/event")
    fun postEvent(@RequestHeader("Authorization") header: String,
                  @RequestBody event: Event): ResponseEntity<Void> {
        val savedEvent = eventRepo.save(event)
        return when (savedEvent) {
            null -> ResponseEntity(HttpStatus.BAD_REQUEST)
            else -> {
                if (savedEvent.event!!.contentEquals("enter")) {
                    println("send push notification")
                    val push = notificationService.pushNotification(savedEvent.deviceId!!)
                    CompletableFuture.allOf(push)
                    try {
                        val response = push?.get()
                        response?.let { println("Firebase pushed successfully") }
                    } catch (ex: ExecutionException) {
                        ex.printStackTrace()
                    }
                }
                return ResponseEntity(HttpStatus.NO_CONTENT)
            }
        }
    }

    @PostMapping("/subscriber")
    fun postSubscriber(@RequestHeader("Authorization") header: String,
                       @RequestBody subscriber: Subscriber): ResponseEntity<Void> {
        return if (subscriberRepo.save(subscriber) == null) ResponseEntity(HttpStatus.BAD_REQUEST)
        else ResponseEntity(HttpStatus.NO_CONTENT)
    }

}