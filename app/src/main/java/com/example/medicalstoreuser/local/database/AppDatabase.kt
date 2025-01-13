package com.example.medicalstoreuser.local.database


import androidx.room.Database
import com.example.medicalstoreuser.local.dao.AddressDao
import com.example.medicalstoreuser.local.model.ClientChoiceModelEntity
import androidx.room.RoomDatabase
import com.example.medicalstoreuser.local.dao.CartDao
import com.example.medicalstoreuser.local.entity.AddressEntity


@Database(entities = [ClientChoiceModelEntity::class,AddressEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cartDao(): CartDao
    abstract fun addressDao() : AddressDao
}


