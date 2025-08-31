package com.check.simownerdetailspakistan.business.domain.usecase

import com.check.simownerdetailspakistan.business.domain.repository.SimOwnerRepository
import com.check.simownerdetailspakistan.business.data.entity.APIState
import com.check.simownerdetailspakistan.business.data.respose.SimOwnerListResponse
import retrofit2.http.Query
import javax.inject.Inject

class SimUseCase @Inject constructor(private val simOwnerRepository: SimOwnerRepository) {
    suspend fun getSimOwnerDetailData(query: String):APIState<SimOwnerListResponse> = simOwnerRepository.getSimOwnerDetailData(query)
}