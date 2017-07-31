package io.ap1.proximityapi.repositories

import io.ap1.proximityapi.entities.Beacon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BeaconRepository : JpaRepository<Beacon, Long>