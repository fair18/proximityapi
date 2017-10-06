package io.ap1.proximityapi.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "networks")
class Network(

        @Id
        var id: String? = null,
        var networkId: Int? = null,
        var name: String? = null,
        var ssid: String? = null

)