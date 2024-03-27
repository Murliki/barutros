package barulin.barutros.peices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardThreadFirst(
    firstText: String,
    secondText: String,
    thirdText: String,
    modifier: Modifier = Modifier,
    stableText: Array<String> = arrayOf("Вал", "D ном.", "D ср.")
) {
    Card(
        modifier = modifier
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxHeight(.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                AlignedThreadElement {
                    Text(
                        text = stableText[0],
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        maxLines = 1
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    Spacer(modifier = Modifier.padding(4.dp))

                    AlignedThreadElement {
                        Text(
                            text = stableText[1],
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            maxLines = 1
                        )
                    }


                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    AlignedThreadElement {
                        Row {
                            Text(
                                text = firstText,
                                fontSize = 16.sp,
                                maxLines = 1


                            )
                            Spacer(modifier = Modifier.padding(horizontal = 1.dp))
                            Text(
                                text = secondText,
                                fontSize = 12.sp,
                                maxLines = 1
                            )
                        }

                    }
                }
                Spacer(modifier = Modifier.padding(horizontal = 8.dp))
                Row(
                    Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center
                ) {
                    AlignedThreadElement {
                        Row {
                            Text(
                                text = stableText[2],
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1

                            )
                            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                            Text(
                                text = thirdText, fontSize = 16.sp,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}