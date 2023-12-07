package com.example.ibmcalculator

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BmiResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi_result)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvIbmResult = findViewById<TextView>(R.id.tvBmiResult)
        val tvIbmNumber = findViewById<TextView>(R.id.tvBmiNumber)

        val result = intent.getFloatExtra("EXTRA_RESULT",0.0f)

        tvIbmResult.text = result.toString()

        val classification = ibmLabel(result)

        tvIbmResult.text = getString(R.string.message_classification,classification)
        tvIbmNumber.text = result.toString()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}

    fun ibmLabel (ibm: Float): String {
        val result = if(ibm < 18.5f){
            "Under weight"
        } else if (ibm in 18.5f ..24.9f){
            "Regular"
        } else if (ibm in 25f..29.9f){
            "Overweight"
        } else if (ibm in 30f..39.9f){
            "Obesity I"
        } else {
            "Obesity II"
        }
        return result
    }
