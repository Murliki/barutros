package barulin.barutros.peices

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import barulin.barutros.R
import barulin.barutros.classes.keyboardButtons

@Composable
fun AppKeyboard(
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onDot: () -> Unit,
    onBackspace: () -> Unit
) {
    val buttonHeight = (LocalConfiguration.current.screenHeightDp * 0.4 * 0.25).dp
    val buttonWidth = (LocalConfiguration.current.screenWidthDp * .25).dp
    val haptic = LocalHapticFeedback.current


    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            items(keyboardButtons) { number ->

                FilledTonalButton(
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        onChange(number)
                              },
                    shape = CircleShape,
                    modifier = Modifier
                        .width(buttonWidth)
                        .height(buttonHeight)
                        .padding(8.dp)
                ) {
                    Text(
                        text = number,
                        fontSize = 20.sp
                    )
                }
            }
        }
        Row{
            FilledTonalButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onDot()
                          },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(.3333f)
                    .height(buttonHeight)
                    .padding(8.dp)
            ) {
                Text(
                    text = ".",
                    fontSize = 20.sp
                )
            }

            FilledTonalButton(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onChange("0")
                          },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(.5f)
                    .height(buttonHeight)
                    .padding(8.dp)
            ) {
                Text(
                    text = "0",
                    fontSize = 20.sp
                )
            }

            Button(
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onBackspace()
                          },
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(buttonHeight)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.baseline_backspace_24),
                    contentDescription = "backspace",

                    )
            }

        }

    }
}

@Preview
@Composable
private fun AppKeyboardPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        AppKeyboard(
            onChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height((LocalConfiguration.current.screenHeightDp * 0.4).dp),
            onDot = {},
            onBackspace = {}
        )

    }
}