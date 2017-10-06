package io.ap1.proximityapi.repository

import io.ap1.proximityapi.model.Network
import org.springframework.data.mongodb.repository.MongoRepository

interface NetworkRepository : MongoRepository<Network, Long>