package io.ap1.proximityapi.entities

import javax.persistence.*

@Entity
@Table(name = "beacons")
class Beacon {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0
    val name: String? = null
    val uuid: String? = null
    val major: Int? = -1
    val minor: Int? = -1
    val latitude: Double? = .0
    val longitude: Double? = .0

}