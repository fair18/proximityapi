package io.ap1.proximityapi.repository

import io.ap1.proximityapi.model.Beacon
import org.springframework.data.mongodb.repository.MongoRepository

interface BeaconRepository : MongoRepository<Beacon, String>