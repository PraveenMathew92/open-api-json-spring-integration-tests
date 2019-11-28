package com.myservice

import com.myservice.controller.dto.Person
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [MyServiceApplication::class])
@AutoConfigureWebTestClient
class MyServiceApplicationTests {
    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    fun `post person`() {
        val id = "c89429aa-10be-11ea-8d71-362b9e155667"

        webTestClient.post().uri("/persons")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .body(Mono.just(Person(firstName = "John", lastName = "Doe")), Person::class.java)
                .exchange()
                .expectStatus()
                .isOk
                .expectBody().json("""
                    {
                        "id": $id
                    }
                """.trimIndent())
    }

}
