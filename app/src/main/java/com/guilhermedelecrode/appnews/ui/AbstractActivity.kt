package com.guilhermedelecrode.appnews.ui

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.guilhermedelecrode.appnews.R

abstract class AbstractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        onInject()

    }
    @LayoutRes
    protected abstract fun getLayout() : Int

    protected abstract fun onInject()
}


