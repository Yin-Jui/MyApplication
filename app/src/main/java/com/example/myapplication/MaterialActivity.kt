package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {
    private val REQUEST_RECORD: Int = 100
    val se = SecretNumber()
    val TAG = "MaterialActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            replay()
        }
        RecordActivity.setText(se.count.toString())
        Log.d(TAG, "OnCreate" + se.secret)
        getSharedPreferences("guess", Context.MODE_PRIVATE)

        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", 0)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)

        Log.d(TAG, "data:  " + count + "/" + nick)

    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.replay))
            .setMessage(getString(R.string.sure))
            .setPositiveButton("OK", { dialog, which ->
                se.reset()
                RecordActivity.setText(se.count.toString())
                input_num.setText("")
            })  //lambda
            .setNeutralButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
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

        RecordActivity.setText(se.count.toString())
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.reminder))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, com.example.myapplication.RecordActivity::class.java)
                    intent.putExtra("COUNTER", se.count)
                    //            startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD) {
            if (resultCode == Activity.RESULT_OK) {

                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult: " + nickname)
                replay()
            }

        }
    }
}
