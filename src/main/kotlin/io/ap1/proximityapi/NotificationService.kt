package io.ap1.proximityapi

import com.fasterxml.jackson.databind.ObjectMapper
import io.ap1.proximityapi.repository.SubscriberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.http.client.support.HttpRequestWrapper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.concurrent.CompletableFuture

@Service
class NotificationService {

    @Value("\${fcm.url}")
    val fcmURL: String? = null

    @Value("\${fcm.key}")
    val fcmKey: String? = null


    @Autowired
    lateinit var subscriberRepo: SubscriberRepository

    private val restTemplate: RestTemplate by lazy { RestTemplate() }

    private val interceptors by lazy {
        listOf<ClientHttpRequestInterceptor>(
                HeaderRequestInterceptor(Pair("Authorization", "key=$fcmKey")),
                HeaderRequestInterceptor(Pair("Content-Type", "application/json")))
    }


    private class HeaderRequestInterceptor(private val header: Pair<String, String>) : ClientHttpRequestInterceptor {

        override fun intercept(request: HttpRequest?, body: ByteArray?, execution: ClientHttpRequestExecution?): ClientHttpResponse {
            val wrapper = HttpRequestWrapper(request)
            val (name, value) = header
            wrapper.headers.set(name, value)
            return execution!!.execute(wrapper, body)
        }
    }

    @Async
    fun pushNotification(deviceId: String): CompletableFuture<String>? {
        val subscriber = subscriberRepo.findByDeviceIdLike(deviceId)
        return if (subscriber == null) null
        else {
            println("found subscriber: ${subscriber.deviceId}")
            restTemplate.interceptors = interceptors
            val fcmResponse = restTemplate.postForObject(fcmURL, getEntity(subscriber.token!!), String::class.java)
            CompletableFuture.completedFuture(fcmResponse)
        }
    }

    private fun getEntity(token: String): HttpEntity<String> {
        val mapper = ObjectMapper()
        val data = mapper.createObjectNode()
        data.put("title", "Ap1 Inc,")
        data.put("content", "http://www.ap1.io")

        val notification = mapper.createObjectNode()
        notification.put("title", "Hello, I am a beacon!")
        notification.put("body", "Check it out the content I delivered")

        val composedBody = mapper.createObjectNode()
        composedBody.put("to", token)
        composedBody.set("notification", notification)
        composedBody.set("data", data)

        val body = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(composedBody)
        println("notification body: $body")
        return HttpEntity(body)
    }
}