package com.dao.utilities.app

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import kotlin.reflect.KClass

abstract class BaseActivity(@LayoutRes layout: Int) : AppCompatActivity(layout) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
    }

    protected abstract fun initializeView()

    protected fun navigationTo(cls: KClass<*>) {
        startActivity(Intent(this, cls.java))
    }
}