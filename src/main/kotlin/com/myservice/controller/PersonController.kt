package com.myservice.controller

import com.myservice.controller.dto.Person
import com.myservice.service.PersonService
import com.github.fge.jsonpatch.JsonPatch
import org.reactivestreams.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
import java.util.*

@RestController
@RequestMapping("/persons")
class PersonController {

    @Autowired
    lateinit var service: PersonService

    @GetMapping(value = ["/{id}"], produces = ["application/json"])
    fun find(@PathVariable id: UUID): Mono<Person> {
        return Person().toMono()
    }

    @GetMapping(produces = ["application/json"])
    fun list(): Flux<Person> {
        return Flux.just(Person(firstName = "Jay"), Person(firstName = "Veeru"))
    }

    @PostMapping(consumes = ["application/json"], produces = ["application/json"])
    fun create(@RequestBody personInput:  Mono<Person>): Mono<Map<String, UUID>> {
        return mapOf("id" to
                UUID.fromString("21a80822-ad80-407e-9c2d-a717b8ea61fe")
        ).toMono()
    }

    @PatchMapping(value = ["/{id}"], consumes = ["application/json"], produces = ["application/json"])
    fun patch(@PathVariable id: UUID, @RequestBody personPatch: JsonPatch): Mono<Person> {
        return Person().toMono()
    }

}