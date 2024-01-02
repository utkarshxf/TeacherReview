package com.orion.templete.Domain.Di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.orion.templete.Data.Repository.AuthRepositoryImpl
import com.orion.templete.Domain.Repository.AuthRepository
import com.orion.templete.util.DataStoreUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun provideDataStoreUtil(@ApplicationContext context: Context): DataStoreUtil =
        DataStoreUtil(context)

    @InstallIn(SingletonComponent::class)
    @Module
    object AppModule {

        @Provides
        fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

        @Provides
        fun providesAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    }
}
