package com.check.simownerdetailspakistan.business.domain.repository

import com.check.simownerdetailspakistan.business.data.entity.APIState
import com.check.simownerdetailspakistan.business.data.respose.SimOwnerListResponse

interface SimOwnerRepository {
    suspend fun getSimOwnerDetailData(query:String):APIState<SimOwnerListResponse>
}