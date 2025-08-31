package com.check.simownerdetailspakistan.ui.main

import com.check.simownerdetailspakistan.business.data.respose.SimOwnerListResponse
import com.check.simownerdetailspakistan.business.domain.repository.SimOwnerRepositoryImp

sealed interface MainUIState {
    data class Success(val simData: SimOwnerListResponse?) : MainUIState
    data class Error(val errorMessage: String?) : MainUIState
    object Loading : MainUIState
    object Default :MainUIState

}