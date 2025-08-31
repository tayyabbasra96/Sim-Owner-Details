package com.check.simownerdetailspakistan.business.data.respose

import java.io.Serializable

data class SimOwnerListResponse( val success: Boolean,
                                 val data: ApiDataDto?,
                                 val error: String?):Serializable
data class ApiDataDto(
    val query: String,
    val total_records: Int,
    val records: List<RecordDto>
):Serializable
data class RecordDto(
    val name: String?,
    val phone: String?,
    val cnic: String?,
    val address: String?
):Serializable
