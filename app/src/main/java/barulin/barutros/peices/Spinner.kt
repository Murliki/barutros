package barulin.barutros.peices

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Spinner(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    data: Array<String>,
    onPageChange: (Int) -> Unit,
    beyondBoundsPageCount: Int = 15,
) {
    fun mathLerp(start: Float, stop: Float, fraction: Float): Float {
        return (1 - fraction) * start + fraction * stop
    }


    Box(modifier = modifier) {
        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                onPageChange(page)
            }
        }
        VerticalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 45.dp),
            beyondBoundsPageCount = beyondBoundsPageCount,
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = data[page],
                    modifier = Modifier
                        .align(Alignment.Center)
                        .graphicsLayer {

                            val pageOffset =
                                ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                            // We animate the alpha, between 50% and 100%
                            alpha = mathLerp(
                                start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                            )

                        },
                    fontSize = lerp(
                        18.sp,
                        12.sp,
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    )
                )
            }
        }
    }
}

