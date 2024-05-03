package br.com.alura.panucci

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.printToLog
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import br.com.alura.panucci.navigation.drinksRoute
import br.com.alura.panucci.navigation.highlightsListRoute
import br.com.alura.panucci.navigation.menuRoute
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            PanucciApp(navController = navController)
        }
    }

    @Test
    fun appNavHost_verifyStartDestination() {
        composeTestRule.onRoot().printToLog("PanucciApp")
        composeTestRule.onNodeWithText("Destaques do dia").assertIsDisplayed()
    }

    @Test
    fun appNavHost_verifyNavigateToMenuScreen() {
        composeTestRule.onRoot().printToLog("PanucciApp")
        composeTestRule.onNodeWithText("Menu")
            .performClick()

        composeTestRule.onAllNodesWithText("Menu").assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, menuRoute)
    }

    @Test
    fun appNavHost_verifyNavigateToDrinksScreen() {
        composeTestRule.onRoot().printToLog("PanucciApp")
        composeTestRule.onNodeWithText("Bebidas")
            .performClick()

        composeTestRule.onAllNodesWithText("Bebidas")
            .assertCountEquals(2)

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, drinksRoute)
    }

    @Test
    fun appNavHost_verifyNavigateToHighlightListScreen() {
        composeTestRule.onRoot().printToLog("PanucciApp")
        composeTestRule.onNodeWithText("Destaques")
            .performClick()

        composeTestRule
            .onNodeWithText("Destaques do dia")
            .assertIsDisplayed()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, highlightsListRoute)
    }

}