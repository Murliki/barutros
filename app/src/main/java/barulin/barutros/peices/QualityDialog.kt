package barulin.barutros.peices

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun QualityDialog(
    visible: Boolean,
    onDismissRequest: () -> Unit,
    title: String,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    qualities: Array<String>
){
    AnimatedVisibility(
        visible = visible,
        modifier = Modifier
            .fillMaxHeight(0.6F)
            .fillMaxWidth(0.9F)
    ) {
        Dialog(
            onDismissRequest = { onDismissRequest() },
        ) {
            Column(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(10))
                    .fillMaxWidth()
                    .fillMaxHeight(0.5F)
            ) {
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Divider()
                LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                    items(qualities) { quality ->
                        OutlinedButton(
                            onClick = { onClick(quality) },
                            modifier = Modifier
                                .width(100.dp)
                                .height(50.dp)
                                .padding(8.dp)
                        ) {
                            Text(text = quality)
                        }
                    }
                }
            }
        }
    }
}