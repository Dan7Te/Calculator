package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calculation = mutableStateOf<String>("")
        val calcResult = mutableStateOf<String>("")

        setContent{
            CalcBlock(
                calculation,
                calcResult
            )
        }
    }
}

@Composable
fun TextPrev(text: String, fontsize:Int = 15) {
    Text(
        text = text,
        modifier = Modifier
            .width(50.dp)
            .height(80.dp),
        fontSize = fontsize.sp
    )
}

@Composable
fun CalcBlock(
    calculation: MutableState<String>,
    calcResult: MutableState<String>
) {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End
    ) {
        Row (
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = calcResult.value,
                fontSize = 40.sp
            )
        }
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                text = calculation.value,
                fontSize = 40.sp
            )
        }
        Row(
            Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.Bottom
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Row() {
                    Button(onClick = {deleteAll(calculation, calcResult)}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("C", 40)
                    }
                    Button(onClick = {deleteSymb(calculation)}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("<=", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "%")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("%", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "/")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("/", 40)
                    }
                }
                Row() {
                    Button(onClick = {changeCalculationsLine(calculation, "7")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("7", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "8")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("8", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "9")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("9", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "*")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("*", 40)
                    }
                }
                Row() {
                    Button(onClick = {changeCalculationsLine(calculation, "4")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("4", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "5")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("5", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "6")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("6", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "-")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("-", 40)
                    }
                }
                Row() {
                    Button(onClick = {changeCalculationsLine(calculation, "1")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("1", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "2")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("2", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "3")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("3", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "+")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("+", 40)
                    }
                }
                Row() {
                    Button(onClick = {changeCalculationsLine(calculation, "3.14")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("e", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, "0")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("0", 40)
                    }
                    Button(onClick = {changeCalculationsLine(calculation, ".")}, modifier = Modifier.padding(5.dp)) {
                        TextPrev(",", 40)
                    }
                    Button(onClick = {calculate(calculation, calcResult)}, modifier = Modifier.padding(5.dp)) {
                        TextPrev("=", 40)
                    }
                }
            }
        }
    }
}

fun changeCalculationsLine(calculation: MutableState<String>, additionalSymb: String){
    calculation.value += additionalSymb
}

fun deleteSymb(calculation: MutableState<String>){
    calculation.value = calculation.value.dropLast(1)
}

fun deleteAll(
    calculation: MutableState<String>,
    calcResult: MutableState<String>
){
    calculation.value = ""
    calcResult.value = ""
}

fun calculate(
    calculation: MutableState<String>,
    calcResult: MutableState<String>
){
    val lst = mutableListOf<String>()
    val lastValue = mutableStateOf<String>("")
    for (i in 0..calculation.value.count()-1){
        if (calculation.value[i] == '.' || calculation.value[i].isDigit()) {
            //lastValue.plus(calculation.value[i])
            lastValue.value += calculation.value[i]
        }
        else if(calculation.value[i] == '+'){
            lst.add(lastValue.value)
            lastValue.value = ""
            lst.add("+")
        }
        else if(calculation.value[i] == '-'){
            lst.add(lastValue.value)
            lastValue.value = ""
            lst.add("-")
        }
        else if(calculation.value[i] == '*'){
            lst.add(lastValue.value)
            lastValue.value = ""
            lst.add("*")
        }
        else if(calculation.value[i] == '/'){
            lst.add(lastValue.value)
            lastValue.value = ""
            lst.add("/")
        }
        else if(calculation.value[i] == '%'){
            lst.add(lastValue.value)
            lastValue.value = ""
            lst.add("%")
        }
    }
    lst.add(lastValue.value)

    while(lst.indexOf("*") != -1){
        val index = lst.indexOf("*")
        lst[index] = (lst[index-1].toDouble() * lst[index+1].toDouble()).toString()
        lst.removeAt(index-1)
        lst.removeAt(index)
    }
    while(lst.indexOf("/") != -1){
        val index = lst.indexOf("/")
        lst[index] = (lst[index-1].toDouble() / lst[index+1].toDouble()).toString()
        lst.removeAt(index-1)
        lst.removeAt(index)
    }
    while(lst.indexOf("%") != -1){
        val index = lst.indexOf("%")
        lst[index] = (lst[index-1].toDouble() % lst[index+1].toDouble()).toString()
        lst.removeAt(index-1)
        lst.removeAt(index)
    }
    while(lst.indexOf("+") != -1){
        val index = lst.indexOf("+")
        lst[index] = (lst[index-1].toDouble() + lst[index+1].toDouble()).toString()
        lst.removeAt(index-1)
        lst.removeAt(index)
    }
    while(lst.indexOf("-") != -1){
        val index = lst.indexOf("-")
        lst[index] = (lst[index-1].toDouble()- lst[index+1].toDouble()).toString()
        lst.removeAt(index-1)
        lst.removeAt(index)
    }

    calculation.value = ""
    calcResult.value = lst[0].toString()
}

