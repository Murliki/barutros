package barulin.barutros.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import barulin.barutros.R
import barulin.barutros.classes.Screens

@Composable
fun StartScreen(
    onClick: (String) -> Unit
) {



    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = if (!isSystemInDarkTheme()) R.drawable.ship_light else R.drawable.ship_dark),
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize(0.75f),
            contentScale = ContentScale.Inside,
        )
        Column {
            Spacer(modifier = Modifier.padding(4.dp))
            
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp),
                onClick = { onClick(Screens.TOLERANCE.name) }
            ) {
                Text(text = "Поля допусков")
                //TODO Image

            }
            FilledTonalButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .padding(8.dp),
                onClick = { onClick(Screens.THREAD.name) }
            ) {
                Text(text = "Резьбы")
                //TODO Image
            }
        }

    }

}