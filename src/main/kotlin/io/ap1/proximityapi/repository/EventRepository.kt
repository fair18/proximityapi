package io.ap1.proximityapi.repository

import io.ap1.proximityapi.model.Event
import org.springframework.data.mongodb.repository.MongoRepository

interface EventRepository: MongoRepository<Event, String>