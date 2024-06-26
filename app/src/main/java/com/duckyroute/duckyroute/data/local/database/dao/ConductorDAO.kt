package com.duckyroute.duckyroute.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.duckyroute.duckyroute.domain.model.local.Conductor

@Dao
interface ConductorDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConductor(conductor: Conductor)

    @Query("SELECT * FROM conductor WHERE id = :id")
    suspend fun getConductor(id: Int): Conductor?

    @Query("UPDATE conductor SET codigo_pais = :codigoPais, numero = :numero WHERE id = :id")
    suspend fun updatePhone(id: Int, codigoPais: String, numero: String)

    @Query("DELETE FROM conductor")
    suspend fun deleteConductor()
}