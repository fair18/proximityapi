package io.ap1.proximityapi

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.FindAndModifyOptions
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update

@Document(collection = "counters")
open class AutoIncrementCounter(@Id var id: String, var seq: Int)

inline fun <reified T : AutoIncrementCounter> MongoOperations.getNextSequence(collectionName: String): Int {
    var counter: T? = this.findAndModify(
            Query.query(Criteria.where("_id").`is`(collectionName)),
            Update().inc("seq", 1),
            FindAndModifyOptions.options().returnNew(true),
            T::class.java)

    if (counter != null) {
        return counter.seq
    }
    counter = AutoIncrementCounter(collectionName, 1) as T
    this.save(counter)
    return 1
}