package com.igormascarenhas.services

import com.igormascarenhas.data.vo.v1.PersonVO
import com.igormascarenhas.data.vo.v2.PersonVO as PersonVOV2
import com.igormascarenhas.exceptions.ResourceNotFoundException
import com.igormascarenhas.mapper.DozerMapper
import com.igormascarenhas.mapper.custom.PersonMapper
import com.igormascarenhas.models.Person
import com.igormascarenhas.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {

    @Autowired
    private lateinit var repository: PersonRepository

    @Autowired
    private lateinit var personMapper: PersonMapper

    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll(): List<PersonVO> {
        logger.info("Finding all people.")

        val people = repository.findAll()
        return DozerMapper.parseListObjects(people, PersonVO::class.java)
    }

    fun findById(id: Long): PersonVO {
        logger.info("Finding one person.")

        var person = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO {
        logger.info("Creating one person with name ${person.firstName}.")

        var entity: Person = DozerMapper.parseObject(person, Person::class.java)

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun createV2(person: PersonVOV2): PersonVOV2 {
        logger.info("Creating one person with name ${person.firstName}.")

        var entity: Person = personMapper.mapVOToEntity(person)

        return personMapper.mapEntityToVO(repository.save(entity))
    }

    fun delete(id: Long) {
        logger.info("Deleting one person with id ${id}.")

        val entity = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        repository.delete(entity)
    }

    fun update(person: PersonVO): PersonVO {
        logger.info("Updating one person with id ${person.id}.")

        val entity = repository.findById(person.id)
            .orElseThrow { ResourceNotFoundException("No records found for this ID!") }

        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }
}
