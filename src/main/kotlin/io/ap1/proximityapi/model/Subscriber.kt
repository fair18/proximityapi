package io.ap1.proximityapi.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "subscribers")
class Subscriber {

    @Indexed
    var deviceId: String? = null
    var deviceType: String? = null
    var deviceOS: String? = null
    var token: String? = null
}