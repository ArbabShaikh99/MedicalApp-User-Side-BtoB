package com.example.medicalstoreuser.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.medicalstoreuser.local.entity.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Insert
    suspend fun insertAddress(address : AddressEntity)

    @Delete
    suspend fun deleteAddress(address: AddressEntity)

    @Query("SELECT * FROM address_table")
    fun getAllAddress() : Flow<List<AddressEntity>>
}