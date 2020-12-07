package com.walterrezende.androidtrivia

import android.os.Bundle

class MainActivity : NavigationCompatActivity() {

    override val navHostFragmentId: Int
        get() = R.id.myNavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}