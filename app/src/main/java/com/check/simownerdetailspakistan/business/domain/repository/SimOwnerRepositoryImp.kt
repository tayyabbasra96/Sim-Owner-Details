package com.check.simownerdetailspakistan.business.domain.repository

import android.util.Log
import com.check.simownerdetailspakistan.business.data.entity.APIState
import com.check.simownerdetailspakistan.business.data.network.SimServiceApi
import com.check.simownerdetailspakistan.business.data.respose.SimOwnerListResponse
import javax.inject.Inject

class SimOwnerRepositoryImp @Inject constructor(private val simServiceApi: SimServiceApi):SimOwnerRepository {
    override suspend fun getSimOwnerDetailData(query: String): APIState<SimOwnerListResponse> {
        return try {
            APIState.Success(simServiceApi.getSimOwnerDetailData(query))

        }catch (e:Exception){
            Log.d("MainEROR",e.toString())
            APIState.Error(e.message?:"")
        }
    }
}