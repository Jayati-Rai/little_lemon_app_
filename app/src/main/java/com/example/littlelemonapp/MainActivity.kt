package com.example.littlelemonapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.littlelemonapp.navigation.MyNavigation
import com.example.littlelemonapp.networkAPI.MenuNetwork
import com.example.littlelemonapp.ui.theme.LittleLemonAppTheme
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.call.body
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.collections.emptyList

private const val MENU_URL =
    "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

class MainActivity : ComponentActivity() {

    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            },
                contentType = ContentType.Text.Plain
            )

        }
    }

    private val database by lazy { AppDatabase.getDatabase(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.menuDao().isEmpty()) {
                try {
                    val menu: MenuNetwork = httpClient.get(MENU_URL).body()
                    val entities = menu.menu.map { item ->
                        MenuItemEntity(
                            id = item.id,
                            title = item.title,
                            description = item.description,
                            price = item.price,
                            image = item.image,
                            category=item.category
                        )
                    }
                    database.menuDao().insertAll(entities)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            LittleLemonAppTheme {
                val databaseMenuItems by database.menuDao().getAll().observeAsState(emptyList())
                val navController = rememberNavController()
                MyNavigation(navController = navController, databaseMenuItems = databaseMenuItems)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        httpClient.close()
    }
}
