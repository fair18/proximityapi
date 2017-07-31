package io.ap1.proximityapi.entities

import javax.persistence.*

@Entity
@Table(name = "events")
class Event {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    var eid: Long = 0
    var type: String? = null
    var id: Int? = null
    var lat: Double? = null
    var lng: Double? = null
    val motion: String? = null
    var heading: String? = null
    var uuid: String? = null
    var timestamp: Long? = null
}