package com.example.higherorderfunction

import android.view.View

// Data class representing a food item
data class FoodItem(val name: String, val price: Double, val rating: Double)

// Higher-Order Function to filter food items
inline fun filterFoodItems(
    foodList: List<FoodItem>,
    filterLogic: (FoodItem) -> Boolean
): List<FoodItem> {
    return foodList.filter(filterLogic)
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