package com.example.medicalstoreuser.DI

import android.content.Context
import androidx.room.Room
import com.example.medicalstoreuser.Data_Layer.ApiService
import com.example.medicalstoreuser.Data_Layer.BASE_URL
import com.example.medicalstoreuser.Repo.repo
import com.example.medicalstoreuser.local.dao.AddressDao
import com.example.medicalstoreuser.local.dao.CartDao
import com.example.medicalstoreuser.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {


    @Provides
    @Singleton
    fun apiProvider() : ApiService {
        return Retrofit.Builder().baseUrl(BASE_URL).client(
            OkHttpClient.Builder().build()
        ).addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiService::class.java)
    }

        @Singleton
        @Provides
        fun ProvideRepo(apiService: ApiService):repo {
            return repo(apiService)
        }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideDatabase(appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "medical_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCartDao(database: AppDatabase): CartDao {
        return database.cartDao()
    }

    @Provides
    @Singleton
    fun provideAddressDao(database: AppDatabase): AddressDao {
        return database.addressDao()
    }

//    @Singleton
//    @Provides
//    fun providerPrefenceManager(
//        @ApplicationContext context: Context
//    )= UserPrefencesManager(context = context)
}