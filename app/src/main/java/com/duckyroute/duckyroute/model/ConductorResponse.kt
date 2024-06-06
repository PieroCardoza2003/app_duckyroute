package com.duckyroute.duckyroute.model

import java.time.LocalDate

data class ConductorResponse(
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val fechaNacimiento: LocalDate,
    val direccion: String,
    val codigo_pais: String,
    val numero: String,

    val email: String,

    val nroLicencia: String,
    val tipoLicencia: String,
    val fechaEmisionLicencia: LocalDate,
    val fechaExpiracionLicencia: LocalDate
)
