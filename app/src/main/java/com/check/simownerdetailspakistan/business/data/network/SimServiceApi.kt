package com.check.simownerdetailspakistan.business.data.network

import com.check.simownerdetailspakistan.business.data.respose.SimOwnerListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SimServiceApi {
    @POST("/api/sim")
    suspend fun getSimOwnerDetailData(@Query(("query") ) query:String): SimOwnerListResponse
}