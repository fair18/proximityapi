package io.ap1.proximityapi.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "events")
class Event {

    @Indexed
    var event: String? = null
    @Indexed
    var zoneType: String? = null
    var zoneId: Int = -1
    var motion: String? = null
    var lat: Double = .0
    var lng: Double = .0
    var deviceId: String? = null
    var deviceType: String? = null

}