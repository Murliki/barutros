package barulin.barutros.ui.tolerance

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.apache.logging.log4j.message.Message
import org.apache.poi.ss.usermodel.Sheet

class ToleranceViewModel : ViewModel() {

    private fun getStringFromExcel(row: Int, column: Int, sheet: Sheet): String {
        if (row == -1 || column == -1){
            return "0.0:0.0"
        }
        return sheet.getRow(row).getCell(column).toString()
    }

    private lateinit var onError: () -> Unit

    fun takeOnError(errorHandle: () -> Unit){
        onError = errorHandle
    }



    private fun qualityToColumnNum(quality: String): Int {

        val num =  when (quality) {
            "G7" -> 2
            "H7" -> 3
            "K7" -> 4
            "M7" -> 5
            "N7" -> 6
            "S7" -> 7
            "Js7" -> 8
            "F8" -> 9
            "H8" -> 10
            "H9" -> 11
            "H11" -> 12
            "H12" -> 13
            "H13" -> 14
            "H14" -> 15
            "H15" -> 16
            "H16" -> 17
            "g6" -> 18
            "h6" -> 19
            "k6" -> 20
            "m6" -> 21
            "n6" -> 22
            "p6" -> 23
            "r6" -> 24
            "s6" -> 25
            "js6" -> 26
            "f7" -> 27
            "js7" -> 28
            "d8" -> 29
            "e8" -> 30
            "h8" -> 31
            "u8" -> 32
            "h9" -> 33
            "d9" -> 34
            "f9" -> 35
            "a11" -> 36
            "b11" -> 37
            "c11" -> 38
            "d11" -> 39
            "h11" -> 40
            "b12" -> 41
            "h12" -> 42
            "h13" -> 43
            "h14" -> 44
            "h15" -> 45
            "h16" -> 46
            else -> {
                return -1
            }
        }

        return num - 1
    }

    private fun sizeToRowNum(size: Double): Int {
        if (size < 0.1) return 45
        if(size > 3150) return 45
        val num =  when(size){
            in 0.1..0.3 -> 3
            in 0.3..0.9 -> 4
            in 1.0..3.0 -> 5
            in 3.0..6.0 -> 6
            in 6.0..10.0 -> 7
            in 10.0..14.0 -> 8
            in 14.0..18.0 -> 9
            in 18.0..24.0 -> 10
            in 24.0..30.0 -> 11
            in 30.0..40.0 -> 12
            in 40.0..50.0 -> 13
            in 50.0..65.0 -> 14
            in 65.0..80.0 -> 15
            in 80.0..100.0 -> 16
            in 100.0..120.0 -> 17
            in 120.0..140.0 -> 18
            in 140.0..160.0 -> 19
            in 160.0..180.0 -> 20
            in 180.0..200.0 -> 21
            in 200.0..225.0 -> 22
            in 225.0..250.0 -> 23
            in 250.0..280.0 -> 24
            in 280.0..315.0 -> 25
            in 315.0..355.0 -> 26
            in 355.0..400.0 -> 27
            in 400.0..450.0 -> 28
            in 450.0..500.0 -> 29
            in 500.0..560.0 -> 30
            in 560.0..630.0 -> 31
            in 630.0..710.0 -> 32
            in 710.0..800.0 -> 33
            in 800.0..900.0 -> 34
            in 900.0..1000.0 -> 35
            in 1000.0..1120.0 -> 36
            in 1120.0..1250.0 -> 37
            in 1250.0..1400.0 -> 38
            in 1400.0..1600.0 -> 39
            in 1600.0..1800.0 -> 40
            in 1800.0..2000.0 -> 41
            in 2000.0..2240.0 -> 42
            in 2240.0..2500.0 -> 43
            in 2500.0..2800.0 -> 44
            in 2800.0..3150.0 -> 45
            else -> {
                return -1
            }
        }
        return num - 1
    }

    private fun getTolerance(quality: String, size: Double, sheet: Sheet): List<Double>{

        var sizeRowNum = sizeToRowNum(size)
        var qualityColumnNum = qualityToColumnNum(quality)
        if (sizeRowNum <= -1 || qualityColumnNum <= -1){
            sizeRowNum = -1
            qualityColumnNum = -1
            reloadUi()
        }
        val string = if (sizeRowNum <= -1) "0.0:0.0" else
            getStringFromExcel(
                sizeRowNum,
                qualityColumnNum,
                sheet = sheet
            )
        val parts = string.split(":")
        return listOf(parts[0].toDouble(), parts[1].toDouble())
    }


    private lateinit var toleranceSheet: Sheet

    private var currentTopMinTolerance: Double = 0.0
    private var currentTopMaxTolerance: Double = 0.0
    private var currentBottomMinTolerance: Double = 0.0
    private var currentBottomMaxTolerance: Double = 0.0

    fun takeSheet(sheet: Sheet){
        toleranceSheet = sheet
    }

    private fun reloadUi(){
        onError()
        currentTopMinTolerance = 0.0
        currentTopMaxTolerance = 0.0
        currentBottomMinTolerance = 0.0
        currentBottomMaxTolerance = 0.0
        updateUiState()
    }

    private val _uiState = MutableStateFlow(ToleranceUiState())
    val uiState: StateFlow<ToleranceUiState> = _uiState.asStateFlow()

    private fun updateUiState(){
        _uiState.update { currentState ->
            currentState.copy(
                topMinTolerance = currentTopMinTolerance,
                topMaxTolerance = currentTopMaxTolerance,
                bottomMinTolerance = currentBottomMinTolerance,
                bottomMaxTolerance = currentBottomMaxTolerance
            )
        }
    }

    fun calculateTolerances(size: Double, quality: String){
        viewModelScope.launch {
            val tolerances = getTolerance(quality, size, toleranceSheet)
            currentTopMinTolerance = tolerances[1] / 1000
            currentTopMaxTolerance = tolerances[0] / 1000
            currentBottomMinTolerance = size + currentTopMinTolerance
            currentBottomMaxTolerance = size + currentTopMaxTolerance

            if (currentBottomMinTolerance < 0.0001 || currentBottomMinTolerance > 3150) currentBottomMinTolerance =
                0.0
            if (currentBottomMaxTolerance < 0.0001 || currentBottomMaxTolerance > 3150) currentBottomMaxTolerance =
                0.0
            updateUiState()
        }
    }




}