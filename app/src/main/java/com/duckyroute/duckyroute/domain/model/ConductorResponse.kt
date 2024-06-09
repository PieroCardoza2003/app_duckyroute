package com.duckyroute.duckyroute.domain.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate

data class ConductorResponse(
    @SerializedName("nombres")
    val nombres: String,
    @SerializedName("apellidos")
    val apellidos: String,
    @SerializedName("dni")
    val dni: String,
    @SerializedName("genero")
    val genero: String,
    @SerializedName("fecha_nacimiento")
    val fechaNacimiento: String,
    @SerializedName("direccion")
    val direccion: String?,
    @SerializedName("codigo_pais")
    val codigo_pais: String?,
    @SerializedName("numero")
    val numero: String?,
    @SerializedName("email")
    val email: String,
    @SerializedName("nro_licencia")
    val nroLicencia: String,
    @SerializedName("tipo_licencia")
    val tipoLicencia: String,
    @SerializedName("fecha_emision_licencia")
    val fechaEmisionLicencia: String,
    @SerializedName("fecha_expiracion_licencia")
    val fechaExpiracionLicencia: String
)
