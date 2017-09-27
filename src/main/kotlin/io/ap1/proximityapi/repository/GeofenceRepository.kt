package io.ap1.proximityapi.repository

import io.ap1.proximityapi.model.Geofence
import org.springframework.data.mongodb.repository.MongoRepository

interface GeofenceRepository : MongoRepository<Geofence, Long>