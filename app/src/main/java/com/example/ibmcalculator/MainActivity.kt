package com.example.ibmcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            val btnIbmCalc : Button = findViewById(R.id.btnBmiCalc)
            val edtWeight : EditText= findViewById(R.id.weight)
            val edtHeight : EditText= findViewById(R.id.height)

        btnIbmCalc.setOnClickListener{

            val heightStr = edtHeight.text.toString()
            val weightStr = edtWeight.text.toString()

            if (heightStr.isNotEmpty() && weightStr.isNotEmpty()) {
                val height: Float = heightStr.toFloat()
                val weight: Float = weightStr.toFloat()

                val result = IBMlogic(height, weight)


                val intent = Intent(this, IbmResultActivity::class.java)
                    .apply {
                        putExtra("EXTRA_RESULT", result)
                    }

                startActivity(intent)
            } else {
                Toast.makeText(this,"Data fields cannot be empty",Toast.LENGTH_LONG).show()
            }
        }
    }
}

    fun IBMlogic (height : Float , weight : Float) : Float {
        val finalHeight: Float = height * height
        val result: Float = weight / finalHeight
        return result

    }