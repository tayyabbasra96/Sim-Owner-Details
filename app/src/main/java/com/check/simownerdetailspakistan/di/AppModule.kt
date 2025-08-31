package com.check.simownerdetailspakistan.di

import com.check.simownerdetailspakistan.business.domain.repository.SimOwnerRepository
import com.check.simownerdetailspakistan.business.domain.repository.SimOwnerRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindBilRepository(billRepositoryImp: SimOwnerRepositoryImp): SimOwnerRepository

}