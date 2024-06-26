package com.duckyroute.duckyroute.domain.usecase

import com.duckyroute.duckyroute.data.repository.local.ConductorRepository
import com.duckyroute.duckyroute.data.repository.remote.ConductorApiRepository
import com.duckyroute.duckyroute.domain.model.local.Conductor

class GetConductorUseCase {

    private val apiRepository = ConductorApiRepository()
    private val dbRepository = ConductorRepository()

    suspend operator fun invoke(id: Int): Conductor? {
        /* verificar si existe en la base de datos local
        val dataLocal = dbRepository.getConductor(id)

        if (dataLocal != null)
            return dataLocal
         */

        // Solicitar la informacion a la api
        val dataApi = apiRepository.getConductor(id)

        if (dataApi != null){
            val conductor = Conductor(
                id,
                dataApi.nombres,
                dataApi.apellidos,
                dataApi.dni,
                dataApi.genero,
                dataApi.fechaNacimiento,
                dataApi.direccion,
                dataApi.codigo_pais,
                dataApi.numero,
                dataApi.email,
                dataApi.nroLicencia,
                dataApi.tipoLicencia,
                dataApi.fechaEmisionLicencia,
                dataApi.fechaExpiracionLicencia
            )

            dbRepository.insertConductor(conductor)
            return conductor
        }
        return null
    }
}