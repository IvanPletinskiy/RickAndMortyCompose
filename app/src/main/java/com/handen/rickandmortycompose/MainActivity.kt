package com.handen.rickandmortycompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.handen.rickandmortycompose.navigation.ComposeNavigator
import com.handen.rickandmortycompose.navigation.ComposeNavigatorImpl
import com.handen.rickandmortycompose.navigation.ComposeScreen
import com.handen.rickandmortycompose.ui.theme.Grey
import com.handen.rickandmortycompose.ui.theme.RickAndMortyComposeTheme

class MainActivity : AppCompatActivity() {
    private val navigator = ComposeNavigatorImpl(Screens.CharactersScreen, finish = {
        finish()
    }) {
        val activityIntent = it.createIntent(this)
        this.startActivity(activityIntent, it.startActivityOptions)
    }

    val cicerone = Cicerone.create().apply {
        getNavigatorHolder().setNavigator(navigator)
    }
    val router = cicerone.router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MyScreenContent(navigator, router)
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    RickAndMortyComposeTheme {
        Surface {
            content()
        }
    }
}

@Composable
fun NavigationContainer(navigator: ComposeNavigator) {
    val currentScreen by navigator.currentScreen.collectAsState()
    currentScreen.compose()
}

@Composable
fun MyScreenContent(navigator: ComposeNavigator, router: Router) {

    Column(modifier = Modifier.fillMaxHeight()) {
        Column(Modifier.weight(1f)) {
            NavigationContainer(navigator = navigator)
        }
        BottomNavigationBar {
            router.replaceScreen(it)
        }
    }
}

@Composable
fun BottomNavigationBar(
    onScreenSelected: (ComposeScreen) -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = listOf(
        NavigationItem.Characters,
        NavigationItem.Episodes,
        NavigationItem.Locations
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Grey
    ) {
        items.forEachIndexed { index, item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Grey,
                unselectedContentColor = Grey.copy(0.4f),
                alwaysShowLabel = true,
                selected = index == selectedIndex.value,
                onClick = {
                    selectedIndex.value = index
                    onScreenSelected(items[index].route)
                }
            )
        }
    }
}

sealed class NavigationItem(var route: ComposeScreen, var icon: Int, var title: String) {
    object Characters : NavigationItem(Screens.CharactersScreen, R.drawable.ic_character, "Characters")
    object Episodes : NavigationItem(Screens.EpisodesScreen, R.drawable.ic_episode, "Episodes")
    object Locations : NavigationItem(Screens.LocationsScreen, R.drawable.ic_location, "Locations")
}

enum class Screens : ComposeScreen {
    CharactersScreen {
        @Composable
        override fun compose() = CharactersScreen()
    },
    EpisodesScreen {
        @Composable
        override fun compose() = EpisodesScreen()
    },
    LocationsScreen {
        @Composable
        override fun compose() = LocationsScreen()
    }
}

@Composable
fun CharactersScreen() {
    Text("Characters")
}

@Composable
fun EpisodesScreen() {
    Text("Episodes")
}

@Composable
fun LocationsScreen() {
    Text("Locations")
}

@Preview("MyScreen preview")
@Composable
fun DefaultPreview() {
    val navigator = ComposeNavigatorImpl(Screens.CharactersScreen)
    val router = Router()
    MyApp {
        MyScreenContent(navigator, router)
    }
}