package com.duckyroute.duckyroute.domain.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Conductor(
    @PrimaryKey
    val id: Int,
    val nombres: String,
    val apellidos: String,
    val dni: String,
    val genero: String,
    val fechaNacimiento: String,
    val direccion: String?,
    val codigo_pais: String?,
    val numero: String?,
    val email: String,
    val nroLicencia: String,
    val tipoLicencia: String,
    val fechaEmisionLicencia: String,
    val fechaExpiracionLicencia: String
)
