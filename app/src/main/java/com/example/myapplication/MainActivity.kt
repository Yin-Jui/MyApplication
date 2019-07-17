package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val se = SecretNumber()
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret: " + se.secret)

    }

    fun check(view: View) {

        val n: Int = input_num.text.toString().toInt()

        Log.d(TAG, "number: " + n)

        val diff = se.validate(n)
        var message = getString(R.string.bing)

        if (diff < 0) {
            message = getString(R.string.big)
        } else if (diff > 0) {
            message = getString(R.string.sma)
        }
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reminder))
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}
