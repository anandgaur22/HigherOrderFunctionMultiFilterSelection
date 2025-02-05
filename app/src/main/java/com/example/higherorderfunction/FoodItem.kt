package com.example.higherorderfunction

import android.view.View

// Data class for food item
// Updated FoodItem data class with "isVegetarian" and "isGlutenFree" properties
data class FoodItem(
    val name: String,
    val price: Double,
    val rating: Double,
    val isVegetarian: Boolean,
    val isGlutenFree: Boolean
)


// Function to filter food items based on selected filters
fun filterFoodItems(foodList: List<FoodItem>, selectedFilters: Set<String>): List<FoodItem> {
    if (selectedFilters.isEmpty()) return foodList

    return foodList.filter { food ->
        var matchesAll = true

        // Apply filters dynamically
        selectedFilters.forEach { filter ->
            when (filter) {
                "Budget Friendly" -> matchesAll = matchesAll && (food.price <= 200) // ✅ ₹200 or less
                "Highly Rated" -> matchesAll = matchesAll && (food.rating >= 4.0) // ✅ Rating 4.0 or more
                "Vegetarian" -> matchesAll = matchesAll && food.isVegetarian // ✅ Vegetarian items
                "Gluten-Free" -> matchesAll = matchesAll && food.isGlutenFree // ✅ Gluten-free items
            }
        }

        matchesAll
    }
}

/*
without higher-order function

fun add(a: Int, b: Int): Int {
    return a + b
}

fun operateOnNumbers(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)  // Operation is a function that works on A and B
}

Example:

// Addition function
fun add(a: Int, b: Int): Int {
    return a + b
}

// Subtraction function
fun subtract(a: Int, b: Int): Int {
    return a - b
}


fun main() {
    val result1 = operateOnNumbers(10, 5, ::add)  // passed Addition function
    val result2 = operateOnNumbers(10, 5, ::subtract)  // passed Subtraction function

    println("Addition Result: $result1")
    println("Subtraction Result: $result2")
}

inline:


inline fun doSomething(action: () -> Unit) {
    action()  // Lambda call
}

fun main() {
    doSomething {
        println("Hello from inline function!")
    }
}

fun main() {
    println("Hello from inline function!")
}

fun setClickListener(button: Button, onClick: () -> Unit) {
    button.setOnClickListener {
        onClick()
    }
}

inline fun setClickListener(button: Button, crossinline onClick: () -> Unit) {
    button.setOnClickListener {
        onClick()
    }
}
* */