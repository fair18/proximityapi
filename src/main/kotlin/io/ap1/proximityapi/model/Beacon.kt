package io.ap1.proximityapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "beacons")
class Beacon {

    @Id
    var id: String? = null
    @Indexed
    var beaconId: Int? = null
    var name: String? = null
    var uuid: String? = null
    var major: Int? = -1
    var minor: Int? = -1
    var lat: Double = .0
    var lng: Double = .0

}