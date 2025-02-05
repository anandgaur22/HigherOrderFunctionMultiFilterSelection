package com.example.higherorderfunction

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.higherorderfunction.ui.theme.HigherOrderFunctionTheme



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
        FoodItem("Pizza", 250.0, 4.5, isVegetarian = false, isGlutenFree = false), // Matches: Highly Rated, Non-Vegetarian
        FoodItem("Burger", 150.0, 4.0, isVegetarian = true, isGlutenFree = false), // Matches: Budget Friendly, Highly Rated, Vegetarian
        FoodItem("Pasta", 200.0, 3.8, isVegetarian = true, isGlutenFree = true), // Matches: Budget Friendly, Vegetarian, Gluten-Free
        FoodItem("Sushi", 500.0, 4.9, isVegetarian = false, isGlutenFree = true), // Matches: Highly Rated, Non-Vegetarian, Gluten-Free
        FoodItem("Salad", 180.0, 4.7, isVegetarian = true, isGlutenFree = true), // Matches: Budget Friendly, Highly Rated, Vegetarian, Gluten-Free
        FoodItem("Steak", 350.0, 4.8, isVegetarian = false, isGlutenFree = true), // Matches: Highly Rated, Non-Vegetarian, Gluten-Free
        FoodItem("Tofu Stir-fry", 220.0, 4.6, isVegetarian = true, isGlutenFree = true) // Matches: Budget Friendly, Highly Rated, Vegetarian, Gluten-Free
    )

    var selectedFilters by remember { mutableStateOf(setOf<String>()) }

    // Apply filters
    val filteredList = filterFoodItems(foodMenu, selectedFilters)

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Food Menu", style = MaterialTheme.typography.displayMedium)
        Spacer(modifier = Modifier.height(8.dp))

        // Filter Buttons
        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(listOf("Budget Friendly", "Highly Rated", "Vegetarian", "Gluten-Free")) { filter ->
                Button(
                    onClick = {
                        selectedFilters = if (filter in selectedFilters)
                            selectedFilters - filter
                        else
                            selectedFilters + filter
                    },
                    modifier = Modifier.padding(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (filter in selectedFilters) Color.Blue else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(filter, color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Show filtered food list
        if (filteredList.isEmpty()) {
            Text(text = "No items match the selected filters.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn {
                items(filteredList) { food ->
                    FoodItemCard(food)
                }
            }
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
            Text(text = if (food.isVegetarian) "Vegetarian" else "Non-Vegetarian")
            Text(text = if (food.isGlutenFree) "Gluten-Free" else "Contains Gluten")
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