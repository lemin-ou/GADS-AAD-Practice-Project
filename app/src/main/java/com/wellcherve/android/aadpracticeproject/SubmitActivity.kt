package com.wellcherve.android.aadpracticeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)
        setSupportActionBar(toolbar2)
        back()
    }

    private fun back() {
        up.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}


