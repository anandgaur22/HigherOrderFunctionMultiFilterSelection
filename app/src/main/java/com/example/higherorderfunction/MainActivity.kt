package com.example.higherorderfunction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.higherorderfunction.ui.theme.HigherOrderFunctionTheme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HigherOrderFunctionTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    FoodFilterScreen()
                }
            }
        }
    }
}



@Composable
fun FoodFilterScreen() {
    val foodMenu = listOf(
        FoodItem("Pizza", 250.0, 4.5),
        FoodItem("Burger", 150.0, 4.0),
        FoodItem("Pasta", 200.0, 3.8),
        FoodItem("Sushi", 500.0, 4.9),
        FoodItem("Sandwich", 100.0, 3.5)
    )

    var selectedFilter by remember { mutableStateOf("All") }


    val filteredList = when (selectedFilter) {
        "Budget Friendly" -> filterFoodItems(foodMenu) { it.price <= 200 }
        "Highly Rated" -> filterFoodItems(foodMenu) { it.rating > 4.0 }
        "Premium" -> filterFoodItems(foodMenu) { it.price > 300 }
        else -> foodMenu
    }


    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item {
            Text(text = "Food Menu", style = MaterialTheme.typography.displayMedium)
            Spacer(modifier = Modifier.height(8.dp))
        }

        item {
            // Filter Buttons inside LazyRow for horizontal scrolling
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(listOf("All", "Budget Friendly", "Highly Rated", "Premium")) { filter ->
                    Button(onClick = { selectedFilter = filter }, modifier = Modifier.padding(4.dp)) {
                        Text(filter)
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }

        // Display Filtered List
        items(filteredList) { food ->
            FoodItemCard(food)
        }
    }
}




@Composable
fun FoodItemCard(food: FoodItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = food.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = "Price: ₹${food.price}")
            Text(text = "Rating: ⭐ ${food.rating}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodFilterScreen() {
    HigherOrderFunctionTheme {
        FoodFilterScreen()
    }
}