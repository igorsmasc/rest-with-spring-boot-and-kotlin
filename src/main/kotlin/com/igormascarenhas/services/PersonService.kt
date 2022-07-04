package com.igormascarenhas.services

import com.igormascarenhas.models.Person
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {

    private val counter: AtomicLong = AtomicLong()

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<Person> {
        logger.info("Finding all people.")

        val people: MutableList<Person> = ArrayList()
        for (i in 0..7) {
            val person = mockPerson(i)
            people.add(person)
        }

        return people
    }

    fun findById(id: Long): Person {
        logger.info("Finding one person.")

        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Igor"
        person.lastName = "Mascarenhas"
        person.address = "Fortaleza - Ceará"
        person.gender = "Male"

        return person
    }

    fun delete(id: Long) {}

    fun update(person: Person): Person {
        return person
    }

    fun create(person: Person): Person {
        return person
    }

    private fun mockPerson(i: Int): Person {
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $i"
        person.lastName = "Last NAme $i"
        person.address = "Mock Address"
        person.gender = "Mock Gender"

        return person
    }

}
