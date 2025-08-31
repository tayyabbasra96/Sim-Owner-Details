package com.check.simownerdetailspakistan.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.check.simownerdetailspakistan.business.data.entity.APIState
import com.check.simownerdetailspakistan.business.data.respose.RecordDto
import com.check.simownerdetailspakistan.business.domain.usecase.SimUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: SimUseCase):ViewModel() {

    private val _mainUiState = MutableStateFlow<MainUIState>(MainUIState.Default)
    val mainUIState= _mainUiState.asStateFlow()


     fun getSimOwnerDetailData(number:String){
         _mainUiState.value=MainUIState.Loading
        viewModelScope.launch() {
            when(val result=useCase.getSimOwnerDetailData(number)){
                is APIState.Success->{
                    _mainUiState.value=MainUIState.Success(result.data)
                }
                is APIState.Error ->{
                    _mainUiState.value=MainUIState.Error(result.message)

                }
            }

        }

    }
    data class Credentials(
        var referenceNumber: String = "",
        var remember: String = ""
    ) {
        fun isNotEmpty(): Boolean {
            return referenceNumber.isNotEmpty()
        }
    }

}