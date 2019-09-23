package com.example.mvvmapplication

import android.content.Context
import android.widget.Toast

class CardClickListenerImpl(private val context: Context) : CardClickListener {
    override fun cardClicked(f: PostModel) {
        Toast.makeText(
            context, "You clicked " + f.title,
            Toast.LENGTH_LONG
        ).show()
    }
}