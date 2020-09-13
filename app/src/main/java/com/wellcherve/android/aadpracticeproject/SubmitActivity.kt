package com.wellcherve.android.aadpracticeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_submit.*

class SubmitActivity : AppCompatActivity(),FormFragment.OnSubmitButtonClickedListener {
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

    override fun onSubmitButtonClickedListener(bundle: Bundle?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val submitDialogFragment = SubmissionDialogFragment()
        submitDialogFragment.arguments = bundle
        fragmentTransaction.replace(R.id.fragment_container, submitDialogFragment, "dialog")
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}


