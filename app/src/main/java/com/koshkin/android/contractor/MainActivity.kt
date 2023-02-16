package com.koshkin.android.contractor

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.koshkin.android.contractor.ui.theme.ContractorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val checked = mutableStateOf(false)
        val counter = mutableStateOf(0)
        setContent {
            HomeScreen(
                checked = checked,
                onCheckedChange = {newCheckedValue ->checked.value = newCheckedValue},
                counter =counter ,
                onCounterClick = {counter.value++})

        }
    }
    @Composable
    fun HomeScreen(
        checked: State<Boolean>,
        counter: State<Int>,
        onCounterClick: () -> Unit,
        onCheckedChange: (Boolean)->Unit,
    ){
        val checkedValue = checked.value
        val counterValue = counter.value
        Column {
            ClickCounter(uppercase = checkedValue,counterValue =counterValue,onCounterClick =  onCounterClick)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = checkedValue, onCheckedChange = onCheckedChange )
                Text(text = "Uppercase", fontSize = 18.sp)
            }

        }
    }

    @Composable
    fun ClickCounter(
        uppercase: Boolean,
        counterValue: Int,
        onCounterClick: () -> Unit
    ){
        val evenOdd = remember(uppercase) {
            EvenOdd(uppercase)
        }
        Text(
            text = "Clicks: $counterValue ${evenOdd.check(counterValue)}",
            modifier = Modifier.clickable(onClick = onCounterClick)
        )
        Log.i(TAG,"ClickCounter(counter = $counterValue uppercase = $uppercase) $evenOdd")
    }
}

class EvenOdd(
    private val uppercase:Boolean
){
    fun check(value: Int):String{
        var result = if (value%2== 0) "even" else "odd"
        if (uppercase) result = result.uppercase()
        return result
    }

    override fun toString(): String {
        return "EvenOdd(uppercase = $uppercase, hashCode = ${hashCode()})"
    }
}