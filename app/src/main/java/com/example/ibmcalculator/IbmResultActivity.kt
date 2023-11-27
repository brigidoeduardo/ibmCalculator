package com.example.ibmcalculator

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IbmResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ibm_result)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvIbmResult = findViewById<TextView>(R.id.tvIbmResult)
        val tvIbmNumber = findViewById<TextView>(R.id.tvIbmNumber)

        val result = intent.getFloatExtra("EXTRA_RESULT",0.0f)

        tvIbmResult.text = result.toString()

        val classification = if(result < 18.5f){
            "Under weight"
        } else if (result in 18.5f ..24.9f){
            "Regular"
        } else if (result in 24.9f..29.9f){
            "Overweight"
        } else if (result in 29.9f..39.9f){
            "Obesity I"
        } else {
            "Obesity II"
        }

        tvIbmResult.text = getString(R.string.message_classification,classification)
        tvIbmNumber.text = result.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}