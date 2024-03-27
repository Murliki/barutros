package barulin.barutros.ui.thread

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.apache.poi.ss.usermodel.Sheet

class ThreadViewModel : ViewModel() {

    private var rollerTolerance: String = "0.0"
    private var rollerDiameter: String = "0.0"
    private var holeTolerance: String = "0.0"
    private var holeDiameter: String = "0.0"
    private var drillDiameter: String = "0.0"

    private lateinit var threadSheet: Sheet

    private lateinit var onError: () -> Unit

    fun takeOnError(errorHandle: () -> Unit){
        onError = errorHandle
    }

    private val _uiState = MutableStateFlow(ThreadUiState())
    val uiState: StateFlow<ThreadUiState> = _uiState.asStateFlow()

    fun getSheet(sheet: Sheet) {
        threadSheet = sheet
    }

    private fun updateUiState() {
        _uiState.update { state ->
            state.copy(
                rollerTolerance = rollerTolerance,
                rollerDiameter = rollerDiameter,
                holeTolerance = holeTolerance,
                holeDiameter = holeDiameter,
                drillDiameter = drillDiameter
            )
        }
    }

    fun testReload(){
        reloadUi()
    }

    private fun reloadUi(){
        onError()
        rollerTolerance = "0.0"
        rollerDiameter = "0.0"
        holeTolerance = "0.0"
        holeDiameter = "0.0"
        drillDiameter = "0.0"
        updateUiState()
    }



    private fun setThread(i: Int) {
        val row = threadSheet.getRow(i)
        drillDiameter = row.getCell(2).toString()
        holeDiameter = row.getCell(3).toString()
        holeTolerance = row.getCell(4).toString()
        rollerDiameter = row.getCell(5).toString()
        rollerTolerance = row.getCell(6).toString()

        updateUiState()
    }

    fun updateThreadScreen(diameter: String, step: String) {


        fun checkThread(i: Int): Boolean {
            return (threadSheet.getRow(i).getCell(0).toString() == diameter.toDouble().toString()
                    && threadSheet.getRow(i).getCell(1).toString().toDouble() == step.toDouble())
        }

        fun inputToRowNum(): Int{

            return when(diameter.toDouble()){
                2.0 -> 1
                2.5 -> 2
                3.0 -> if (checkThread(3)) 3 else 4
                4.0 -> if (checkThread(5)) 5 else 6
                5.0 -> if (checkThread(7)) 7 else 8
                6.0 -> if (checkThread(9)) 9 else if (checkThread(10)) 10 else 11
                8.0 -> if (checkThread(12)) 12 else 13
                10.0 -> if (checkThread(14)) 14 else if (checkThread(15)) 15
                    else if (checkThread(16)) 16 else 17
                12.0 -> {for (i in 18..23) if (checkThread(i)) return i ; reloadUi(); return 0 }

                14.0 -> if (checkThread(23)) 23 else if (checkThread(24)) 24 else 25
                16.0 -> if (checkThread(26)) 26 else if (checkThread(27)) 27 else 28
                18.0 -> if (checkThread(29)) 29 else if (checkThread(30)) 30 else 31
                20.0 -> if (checkThread(32)) 32 else if (checkThread(33)) 33 else 34
                22.0 -> { for (i in 35..39) if (checkThread(i)) return i; reloadUi(); return 0 }
                24.0 -> if (checkThread(39)) 39 else if (checkThread(40)) 40 else 41
                27.0 -> if (checkThread(42)) 42 else if (checkThread(43)) 43 else 44
                30.0 -> if (checkThread(45)) 45 else if (checkThread(46)) 46 else 47
                33.0 -> { for (i in 48..52) if (checkThread(i)) return i; reloadUi(); return 0 }
                36.0 -> { for (i in 52..56) if (checkThread(i)) return i; reloadUi(); return 0 }
                39.0 -> { for (i in 56..60) if (checkThread(i)) return i; reloadUi(); return 0 }
                42.0 -> { for (i in 60..64) if (checkThread(i)) return i; reloadUi(); return 0 }
                45.0 -> { for (i in 64..68) if (checkThread(i)) return i; reloadUi(); return 0 }
                48.0 -> { for (i in 68..72) if (checkThread(i)) return i; reloadUi(); return 0 }
                50.0 -> 72
                52.0 -> { for (i in 73..77) if (checkThread(i)){ return i}; reloadUi(); return 0 }
                56.0 -> if (checkThread(77)) 77 else if (checkThread(78)) 78 else 79
                60.0 -> if (checkThread(80)) 80 else if (checkThread(81)) 81 else 82
                64.0 -> { for (i in 83..87) if (checkThread(i)) return i; reloadUi(); return 0 }
                68.0 -> if (checkThread(87)) 87 else 88
                72.0 -> { for (i in 89..92) if (checkThread(i)) return i; reloadUi(); return 0 }
                76.0 -> if (checkThread(92)) 92 else 93
                80.0 -> if (checkThread(94)) 94 else 95
                85.0 -> if (checkThread(96)) 96 else 97
                90.0 -> if (checkThread(98)) 98 else 99
                 else -> 0
            }
        }
        val rowNum = inputToRowNum()
        if (rowNum != 0){
            setThread(inputToRowNum())
        } else {
            reloadUi()
        }


    }

}

