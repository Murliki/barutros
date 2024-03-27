package barulin.barutros.screens

import android.icu.text.DecimalFormat
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barulin.barutros.classes.lettersHoles
import barulin.barutros.classes.lettersRollers
import barulin.barutros.classes.numbersHolesNot_H
import barulin.barutros.classes.numbersHoles_F
import barulin.barutros.classes.numbersHoles_H
import barulin.barutros.classes.numbersRollers_a_c
import barulin.barutros.classes.numbersRollers_b
import barulin.barutros.classes.numbersRollers_d
import barulin.barutros.classes.numbersRollers_e_u
import barulin.barutros.classes.numbersRollers_f
import barulin.barutros.classes.numbersRollers_h
import barulin.barutros.classes.numbersRollers_js
import barulin.barutros.classes.numbersRollers_k_m_n_p_r_s
import barulin.barutros.peices.AppKeyboard
import barulin.barutros.peices.Spinner
import barulin.barutros.peices.VerticalDivider
import barulin.barutros.ui.tolerance.ToleranceUiState
import barulin.barutros.ui.tolerance.ToleranceViewModel
import com.chargemap.compose.numberpicker.ListItemPicker
import kotlinx.coroutines.launch


@Composable
fun ToleranceScreen(
    toleranceViewModel: ToleranceViewModel, toleranceUiState: ToleranceUiState, onBack: () -> Unit
) {
    val df = DecimalFormat("#.###")
    df.roundingMode = 1

    val firstList = listOf("Вал", "Отверстие")
    var firstValue by remember { mutableStateOf(firstList[0]) }

    var secondList by remember { mutableStateOf(lettersRollers) }
    var secondValue by remember { mutableStateOf(secondList[0]) }

    var thirdList by remember { mutableStateOf(numbersRollers_h) }
    var thirdValue by remember { mutableStateOf(thirdList[0]) }

    val coroutineScope = rememberCoroutineScope()
    var input by remember { mutableStateOf("0") }

    val textStyle = TextStyle(color = if (isSystemInDarkTheme()) Color.White else Color.Black)


    LaunchedEffect(input) {
        val size = input.toDoubleOrNull()
        if (size != null && size != 0.0) toleranceViewModel.calculateTolerances(
            size, secondValue + thirdValue
        )
    }

    BackHandler {
        onBack()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = input,
                fontSize = 30.sp,
                maxLines = 1
            )
            Spacer(modifier = Modifier.padding(vertical = 12.dp))
            Row {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Min",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = df.format(toleranceUiState.topMinTolerance),
                        maxLines = 1
                    )
                    Text(
                        text = df.format(toleranceUiState.bottomMinTolerance),
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 75.dp))

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Max", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Text(
                        text = df.format(toleranceUiState.topMaxTolerance),
                        maxLines = 1
                    )
                    Text(
                        text = df.format(toleranceUiState.bottomMaxTolerance),
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(125.dp)
            ) {

                val haptic = LocalHapticFeedback.current

                ListItemPicker(
                    value = firstValue,
                    onValueChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        firstValue = it
                        val firstPage = firstValue == firstList[0]
                        secondList = if (firstPage) lettersRollers
                        else lettersHoles
                        thirdList = if (firstPage) numbersRollers_h
                        else numbersHolesNot_H
                        secondValue = secondList[0]
                        thirdValue = thirdList[0]


                        val size = input.toDoubleOrNull()
                        if (size != null && size != 0.0) toleranceViewModel.calculateTolerances(
                            size, secondValue + thirdValue
                        )

                    },
                    list = firstList,
                    dividersColor = Color.Transparent,
                    modifier = Modifier
                        .width(150.dp)
                        .height(125.dp),
                    textStyle = textStyle
                )


                VerticalDivider(modifier = Modifier.height(75.dp))

                ListItemPicker(
                    value = secondValue,
                    onValueChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        secondValue = it
                        if (firstValue == firstList[0]) {
                            thirdList = when (secondValue) {
                                "h" -> numbersRollers_h
                                "k", "m", "n", "p", "r", "s" -> numbersRollers_k_m_n_p_r_s
                                "js" -> numbersRollers_js
                                "f" -> numbersRollers_f
                                "d" -> numbersRollers_d
                                "e", "u" -> numbersRollers_e_u
                                "a", "c" -> numbersRollers_a_c
                                "b" -> numbersRollers_b
                                else -> throw Exception("Такого быть не могло, secondValue == $secondValue")
                            }
                        } else {
                            thirdList = when (secondValue) {
                                "F" -> numbersHoles_F
                                "H" -> numbersHoles_H
                                else -> numbersHolesNot_H
                            }
                        }
                        thirdValue = thirdList[0]


                        val size = input.toDoubleOrNull()
                        if (size != null && size != 0.0) toleranceViewModel.calculateTolerances(
                            size, secondValue + thirdValue
                        )


                    },
                    list = secondList,
                    dividersColor = Color.Transparent,
                    modifier = Modifier
                        .width(150.dp),
                    textStyle = textStyle
                )

                VerticalDivider(modifier = Modifier.height(75.dp))

                ListItemPicker(

                    value = thirdValue,
                    onValueChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        thirdValue = it
                        coroutineScope.launch {
                            val size = input.toDoubleOrNull()
                            if (size != null && size != 0.0) toleranceViewModel.calculateTolerances(
                                size, secondValue + thirdValue
                            )

                        }
                    },
                    list = thirdList,
                    dividersColor = Color.Transparent,
                    modifier = Modifier
                        .width(150.dp)
                        .height(125.dp),
                    textStyle = textStyle
                )

            }

            if (input.toDoubleOrNull() != null) {
                val notNullDouble = input.toDouble()
                Spacer(modifier = Modifier.padding(16.dp))
                AnimatedVisibility(visible = notNullDouble > 3150) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .height(64.dp),
                    ) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Значение слишком большое",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.Center),
                                maxLines = 1
                            )
                        }
                    }
                }
                AnimatedVisibility(visible = notNullDouble < 0.01) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .height(64.dp),
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Значение слишком маленькое",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                modifier = Modifier.align(Alignment.Center),
                                maxLines = 1
                            )
                        }
                    }
                }
            }

        }

        AppKeyboard(
            onChange = {
                if (input == "" || input == "0") {
                    input = it
                } else {
                    input += it
                }

            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            onDot = { if (input == "") input = "0." else input += "." },
            onBackspace = { if (input != "") input = input.substring(0, input.length - 1) },
        )
    }
}