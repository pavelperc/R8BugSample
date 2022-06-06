package com.example.r8bugsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class MyDelegate : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return "aaa"
    }
}

class Repository {
    val prop by MyDelegate()
    val prop1 = "abc"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.text = Repository::prop1.returnType.toString() // works ok
        textView.text = Repository::prop.returnType.toString() // crash here
    }
}