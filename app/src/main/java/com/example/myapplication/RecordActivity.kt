package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_record.*

class RecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)
        val count = intent.getIntExtra("COUNTER", 0)
        counter.setText(count.toString())

        //OnClickListener

        save.setOnClickListener { view ->

            val nick = nickname.text.toString()
            getSharedPreferences("guess", Context.MODE_PRIVATE)
                .edit()
                .putInt("REC_COUNTER", count)
                .putString("REC_NICKNAME", nick)
                .apply()
        }
        getSharedPreferences("guess", Context.MODE_PRIVATE)

        val count2 = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", 0)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)

        Log.d("RecordActivity", "data:  " + count2 + "/" + nick)

    }
}
