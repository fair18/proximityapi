package io.ap1.proximityapi

import io.ap1.proximityapi.model.Beacon
import io.ap1.proximityapi.model.Geofence
import io.ap1.proximityapi.model.Network
import io.ap1.proximityapi.model.Zones
import io.ap1.proximityapi.repository.BeaconRepository
import io.ap1.proximityapi.repository.GeofenceRepository
import io.ap1.proximityapi.repository.NetworkRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class BeaconRepositoryTest {

    @Autowired
    private lateinit var mongo: MongoOperations
    @Autowired
    private lateinit var beaconRepository: BeaconRepository
    @Autowired
    private lateinit var geofenceRepository: GeofenceRepository
    @Autowired
    private lateinit var networkRepository: NetworkRepository

    @Before
    @Throws(Exception::class)
    fun setUp() {

        beaconRepository.deleteAll()
        val beacon1 = Beacon()
        beacon1.beaconId = mongo.getNextSequence<AutoIncrementCounter>("beacons")
        beacon1.name = "AP1 INC."
        beacon1.uuid = "23a01af0-232a-4518-9c0e-323fb773f5ef"
        beacon1.major = 3999
        beacon1.minor = 3999
        beacon1.lat = 43.6669815
        beacon1.lng = -79.3852928

        Assert.assertNull(beacon1.id)

        beaconRepository.save(beacon1)
        Assert.assertNotNull(beacon1.id)


        geofenceRepository.deleteAll()
        val geofence = Geofence()
        geofence.geofenceId = mongo.getNextSequence<AutoIncrementCounter>("geofences")
        geofence.name = "ROGERS CENTER"
        geofence.lat = 43.641580
        geofence.lng = -79.38912
        geofence.radius = 30f

        Assert.assertNull(geofence.id)
        geofenceRepository.save(geofence)
        Assert.assertNotNull(geofence.id)

        networkRepository.deleteAll()
        val network = Network()
        network.networkId = mongo.getNextSequence<AutoIncrementCounter>("networks")
        network.name = "AP1"
        network.ssid = "Sweet Network"

        Assert.assertNull(network.id)
        networkRepository.save(network)
        Assert.assertNotNull(network.id)

        val network1 = Network()
        network1.networkId = mongo.getNextSequence<AutoIncrementCounter>("networks")
        network1.name = "OWENERGY"
        network1.ssid = "OWENERGY-Guest"

        Assert.assertNull(network1.id)
        networkRepository.save(network1)
        Assert.assertNotNull(network1.id)

    }

    @Test
    fun fetchZones() {

        val zones = ZonesResponse(Zones(beaconRepository.findAll(), geofenceRepository.findAll(), networkRepository.findAll()))
        print(zones)

    }

}
