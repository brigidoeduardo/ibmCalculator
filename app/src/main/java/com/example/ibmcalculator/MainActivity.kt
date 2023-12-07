package com.example.ibmcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.NumberPicker
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIbmCalc: Button = findViewById(R.id.btnBmiCalc)
        val seekBarWeight: SeekBar = findViewById(R.id.seekBarWeight)
        val tvWeight: TextView = findViewById(R.id.tvWeight)
        val numberPickerMeters: NumberPicker = findViewById(R.id.numberPickerMeters)
        val numberPickerCentimeters: NumberPicker = findViewById(R.id.numberPickerCentimeters)

        numberPickerMeters.minValue = 0
        numberPickerMeters.maxValue = 10
        numberPickerMeters.wrapSelectorWheel = true

        numberPickerCentimeters.minValue = 0
        numberPickerCentimeters.maxValue = 99
        numberPickerCentimeters.wrapSelectorWheel = true

        numberPickerMeters.setOnValueChangedListener { _, _, newVal ->
            if (newVal == 2) {
                numberPickerCentimeters.maxValue = 99
                numberPickerCentimeters.wrapSelectorWheel = true
            } else {
                numberPickerCentimeters.maxValue = 99
                numberPickerCentimeters.wrapSelectorWheel = true
            }
        }


        seekBarWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                tvWeight.text = getString(R.string.selected_weight, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        btnIbmCalc.setOnClickListener {

            val heightMeters: Int = numberPickerMeters.value
            val heightCentimeters: Int = numberPickerCentimeters.value
            val weightStr: String = tvWeight.text.toString()

            if (heightMeters >= 0 && weightStr.isNotEmpty()) {
                val heightInMeters: Float = heightMeters + heightCentimeters / 100.0f
                val result = BMIlogic(heightInMeters, weightStr)

                val intent = Intent(this, BmiResultActivity::class.java).apply {
                    putExtra("EXTRA_RESULT", result)
                }

                startActivity(intent)
            } else {
                Toast.makeText(this, "Data fields cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
    }
    fun BMIlogic(height: Float, weightStr: String): Float {
        val numericalWeight = weightStr.replace("[^0-9.]".toRegex(), "").toFloat()
        val finalHeight: Float = height * height
        return numericalWeight / finalHeight
    }
}


