package io.ap1.proximityapi

import io.ap1.proximityapi.entities.Event
import io.ap1.proximityapi.repositories.BeaconRepository
import io.ap1.proximityapi.repositories.EventRepository
import io.ap1.proximityapi.repositories.GeofenceRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.web.bind.annotation.*
import java.lang.IllegalStateException

@RestController
@RequestMapping(produces = arrayOf(APPLICATION_JSON_UTF8_VALUE))
class ProximityController {

    @Value("\${api.key}")
    val apiKey: String? = null

    @Autowired
    lateinit var eventRepository: EventRepository
    @Autowired
    lateinit var beaconRepository: BeaconRepository
    @Autowired
    lateinit var geofenceRepository: GeofenceRepository

    @GetMapping("/zones")
    fun getZones(@RequestHeader("Api-Key") key: String,
                 @RequestParam("lat") latitude: Double,
                 @RequestParam("lng") longitude: Double): ZonesResponse? {
        return ZonesResponse(beaconRepository.findAll(), geofenceRepository.findAll())
    }


    @PostMapping("/event")
    fun enter(@RequestHeader("Api-Key") key: String, @RequestBody event: Event): Long {
        if (!apiKey?.contentEquals(key)!!) throw IllegalStateException("api-key is not valid")
        return eventRepository.save(event).eid
    }
}
