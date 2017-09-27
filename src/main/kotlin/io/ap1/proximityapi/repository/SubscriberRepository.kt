package io.ap1.proximityapi.repository

import io.ap1.proximityapi.model.Subscriber
import org.springframework.data.mongodb.repository.MongoRepository

interface SubscriberRepository : MongoRepository<Subscriber, String> {

    fun findByDeviceIdLike(deviceId: String): Subscriber?


}