package io.ap1.proximityapi.entities

import javax.persistence.*

@Entity
@Table(name = "geofences")
class Geofence {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    val name: String? = null
    val geopath: String? = null

}