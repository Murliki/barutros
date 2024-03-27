package barulin.barutros

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.layout.fillMaxSize
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Scaffold
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import barulin.barutros.classes.Screens
import barulin.barutros.screens.StartScreen
import barulin.barutros.screens.ThreadScreen
import barulin.barutros.screens.ToleranceScreen
import barulin.barutros.ui.theme.BarutrosTheme
import barulin.barutros.ui.thread.ThreadViewModel
import barulin.barutros.ui.tolerance.ToleranceViewModel
import kotlinx.coroutines.launch
import org.apache.poi.ss.usermodel.WorkbookFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BarutrosTheme {
                val scaffoldState = rememberScaffoldState()
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(scaffoldState = scaffoldState) {
                    val padding = it
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        val coroutineScope = rememberCoroutineScope()

                        val haptic = LocalHapticFeedback.current


                        fun onError(){
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            navController.navigate(Screens.START.name)
                            coroutineScope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = "Произошла ошибка",
                                    actionLabel = "Хорошо"
                                )
                            }
                        }

                        val workbook =
                            WorkbookFactory.create(LocalContext.current.assets.open("table.xlsx"))
                        val toleranceSheet = workbook.getSheetAt(0)
                        val carvingSheet = workbook.getSheetAt(1)

                        val toleranceViewModel: ToleranceViewModel = viewModel()
                        val toleranceUiState by toleranceViewModel.uiState.collectAsState()
                        toleranceViewModel.takeSheet(toleranceSheet)
                        toleranceViewModel.takeOnError {
                            onError()
                        }

                        val threadViewModel: ThreadViewModel = viewModel()
                        val threadUiState by threadViewModel.uiState.collectAsState()
                        threadViewModel.getSheet(carvingSheet)
                        threadViewModel.takeOnError {
                            onError()
                        }



                        val screenChangeAnimationSpecFloat: FiniteAnimationSpec<Float> = tween(300)



                        NavHost(
                            navController = navController,
                            startDestination = Screens.START.name,
                        ) {

                            composable(route = Screens.START.name) {
                                StartScreen { route -> navController.navigate(route = route) }
                            }
                            composable(route = Screens.TOLERANCE.name,
                                enterTransition = { scaleIn(animationSpec = screenChangeAnimationSpecFloat) },
                                exitTransition = { fadeOut(animationSpec = screenChangeAnimationSpecFloat) }) {
                                ToleranceScreen(toleranceViewModel = toleranceViewModel,
                                    toleranceUiState = toleranceUiState,
                                    onBack = { navController.navigateUp() })
                            }

                            composable(route = Screens.THREAD.name,
                                enterTransition = { scaleIn(animationSpec = screenChangeAnimationSpecFloat) },
                                exitTransition = { fadeOut(animationSpec = screenChangeAnimationSpecFloat) }
                            ) {
                                ThreadScreen(
                                    threadViewModel = threadViewModel,
                                    threadUiState = threadUiState,
                                    onBack = { navController.navigateUp() }
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}


