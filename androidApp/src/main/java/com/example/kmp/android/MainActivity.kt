package com.example.kmp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.kmp.Greeting
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {

                val offset = remember { Animatable(initialValue = 0f) }

                Row(
                    Modifier
                    .background(Color.White)
                    .padding(top = 32.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .weight(0.8f)
                            .zIndex(1f)
                    ) {

                        var barWidth by remember { mutableFloatStateOf(0f) }
                        val scope = rememberCoroutineScope()

                        Box(
                            modifier = Modifier.fillMaxHeight()
                                .background(Color.Blue)
                                .onSizeChanged {
                                    barWidth = it.width.toFloat()
                                }
                        ) {
                            Column {
                                (listOf(1,2,3,4,5)).forEach {
                                    Row(
                                        modifier = Modifier.width(200.dp).background(Color.Cyan) .offset {
                                            IntOffset(offset.value.toInt() - barWidth.toInt(), 0)
                                        },
                                        verticalAlignment = Alignment.CenterVertically

                                    ) {


                                        Text(
                                            text = "Menu $it",
                                            fontSize = 24.sp,
                                            modifier = Modifier.background(Color.Red)
                                                .padding(vertical = 8.dp)

                                            ,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Menu $it",
                                            fontSize = 24.sp,
                                            modifier = Modifier.background(Color.Yellow)
                                                .padding(vertical = 8.dp)
                                            ,
                                            color = Color.White
                                        )
                                        Text(
                                            text = "Menu $it",
                                            fontSize = 24.sp,
                                            modifier = Modifier.background(Color.Green)
                                                .padding(vertical = 8.dp)
                                            ,
                                            color = Color.White
                                        )

                                    }
                                }
                            }
                        }

                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset {
                                    IntOffset(offset.value.toInt(), 0)
                                }.pointerInput(barWidth) {
                                    detectHorizontalDragGestures(
                                        onHorizontalDrag = { _, dragAmount ->
                                            scope.launch {
                                                val newOffset = (offset.value + dragAmount)
                                                    .coerceIn(0f, barWidth)
                                                offset.snapTo(newOffset)
                                            }
                                        },

                                        onDragEnd = {
                                            if (offset.value >= barWidth / 2f) {
                                                scope.launch {
                                                    offset.animateTo(barWidth)
                                                }
                                            }
                                            else {
                                                scope.launch {
                                                    offset.animateTo(0f)
                                                }
                                            }
                                        }
                                    )
                                }

                            ,
                        ) {
                            Box {
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth().background(Color.Green)
                                )  {
                                    items((0..100).toList()) {
                                        Text(
                                            text = "Menu $it",
                                            fontSize = 24.sp,
                                            modifier = Modifier.background(Color.Red)
                                                .padding(vertical = 8.dp),
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Box(
                        modifier = Modifier.weight(0.2f)
                            .zIndex(1f)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize().background(Color.Yellow)
                        ) {  }
                    }
                }

            }
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}
