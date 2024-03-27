package barulin.barutros.screens

//noinspection UsingMaterialAndMaterial3Libraries
import android.icu.text.DecimalFormat
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import barulin.barutros.classes.diameters
import barulin.barutros.classes.steps
import barulin.barutros.classes.steps_2
import barulin.barutros.peices.CardThreadFirst
import barulin.barutros.peices.VerticalDivider
import barulin.barutros.ui.thread.ThreadUiState
import barulin.barutros.ui.thread.ThreadViewModel
import com.chargemap.compose.numberpicker.ListItemPicker

@Composable
fun ThreadScreen(
    onBack: () -> Unit, threadViewModel: ThreadViewModel, threadUiState: ThreadUiState
) {
    val df = DecimalFormat("#.##")
    df.roundingMode = 1
    val haptic = LocalHapticFeedback.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        BackHandler {
            onBack()
        }

        val textStyle = TextStyle(color = if (isSystemInDarkTheme()) Color.White else Color.Black)

        val firstList = diameters
        var firstValue by remember { mutableStateOf(firstList[0]) }

        var secondList by remember { mutableStateOf(steps[firstValue.toDouble()] ?: steps_2) }
        var secondValue by remember { mutableStateOf(secondList[0]) }

        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(16.dp))

            Row {

                ListItemPicker(
                    value = firstValue,
                    onValueChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        firstValue = it
                        secondList = steps[firstValue.toDouble()]
                            ?: throw Exception("List in steps not found, firstValue = $firstValue")
                        secondValue = secondList[0]
                        threadViewModel.updateThreadScreen(firstValue, secondValue)
                    },
                    list = firstList,
                    dividersColor = Color.Transparent,
                    modifier = Modifier
                        .width(150.dp)
                        .height(125.dp),
                    textStyle = textStyle
                )

                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                VerticalDivider(modifier = Modifier.height(100.dp))
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                ListItemPicker(
                    value = secondValue,
                    onValueChange = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        secondValue = it
                        threadViewModel.updateThreadScreen(firstValue, secondValue)
                    },
                    list = secondList,
                    dividersColor = Color.Transparent,
                    modifier = Modifier
                        .width(150.dp)
                        .height(125.dp),
                    textStyle = textStyle
                )


            }

            Spacer(modifier = Modifier.padding(24.dp))

            CardThreadFirst(
                firstText = threadUiState.rollerDiameter,
                secondText = if (threadUiState.rollerTolerance.toDouble() > 0) '+' + threadUiState.rollerTolerance
                else "" + threadUiState.rollerTolerance,
                thirdText = df.format(threadUiState.rollerDiameter.toDouble() + threadUiState.rollerTolerance.toDouble() / 2.0),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(85.dp)
            )

            Spacer(modifier = Modifier.padding(16.dp))

            CardThreadFirst(
                firstText = threadUiState.holeDiameter,
                secondText = if (threadUiState.holeTolerance.toDouble() > 0) '+' + threadUiState.holeTolerance
                else "" + threadUiState.holeTolerance,
                thirdText = df.format(threadUiState.holeDiameter.toDouble() + threadUiState.holeTolerance.toDouble() / 2.0),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(85.dp),
                stableText = arrayOf("Отверстие", "D ном.", "D ср.")
            )

            Spacer(modifier = Modifier.padding(16.dp))
            AnimatedVisibility(visible = threadUiState.rollerDiameter.toDouble() > 0) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(64.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Диаметр сверла    ${threadUiState.drillDiameter}",
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}