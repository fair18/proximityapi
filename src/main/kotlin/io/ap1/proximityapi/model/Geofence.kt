package io.ap1.proximityapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "geofences")
class Geofence(

        @Id
        var id: String? = null,
        var geofenceId: Int? = null,
        var name: String? = null,
        var lat: Double = .0,
        var lng: Double = .0,
        var radius: Float = 0f

)
