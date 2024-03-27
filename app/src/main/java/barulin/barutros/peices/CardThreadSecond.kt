package barulin.barutros.peices

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val array = arrayOf("Отв.", "D.", "Допуск")

@Composable
fun CardThreadSecond(
    firstText: String,
    secondText: String,
    modifier: Modifier = Modifier,
    stableText: Array<String> = array,
){
    Card (

        modifier = modifier
    ){


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            AlignedThreadElement {
                Text(
                    text = stableText[0],
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(.15f)
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 20.dp))
            AlignedThreadElement {
                Text(
                    text = stableText[1],
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            AlignedThreadElement {
                Text(
                    text = firstText,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth(.35f)
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            AlignedThreadElement {
                Row {
                    Text(
                        text = stableText[2],
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Text(
                        text = secondText, fontSize = 18.sp
                    )
                }
            }
        }
    }
}