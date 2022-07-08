package com.igormascarenhas.data.vo.v1

import com.fasterxml.jackson.annotation.JsonPropertyOrder

@JsonPropertyOrder("id", "address", "firstNAme", "lastName", "gender")
data class PersonVO(
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var gender: String = "",
)
