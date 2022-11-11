package com.example.top

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(),   View.OnClickListener{
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
        override fun onClick(view: View?) {
            if(view?.id == R.id.btn_calculate){
                val inputLen = edtLength.text.toString().trim()
                val inputWidth = edtWidth.text.toString().trim()
                val inputHeight = edtHeight.text.toString().trim()

                var isEmptyFields = false
                var isInvalidDouble = false

                if (TextUtils.isEmpty(inputLen)){
                    isEmptyFields = true
                    edtLength.error = "Field ini tidak boleh kosong"
                }
                if (TextUtils.isEmpty(inputWidth)){
                    isEmptyFields = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                }
                if (TextUtils.isEmpty(inputHeight)){
                    isEmptyFields = true
                    edtHeight.error = "Field ini tidak boleh kosong"
                }

                val length = convertToDouble(inputLen)
                val width = convertToDouble(inputWidth)
                val height = convertToDouble(inputHeight)


                if (length == null){
                    isInvalidDouble = true
                    edtLength.error = "nilai tidak valid"
                }

                if (width == null){
                    isInvalidDouble = true
                    edtWidth.error = "nilai tidak valid"
                }


                if (height == null){
                    isInvalidDouble = true
                    edtHeight.error = "nilai tidak valid"
                }

                if(!isEmptyFields && !isInvalidDouble){
                    val volume = height!!.toDouble() * length!!.toDouble() * width!!.toDouble()
                    tvResult.text = volume.toString()
                }
            }
        }

        override fun onSaveInstanceState(outState: Bundle) {
            super.onSaveInstanceState(outState)
            outState.putString(STATE_RESULT, tvResult.text.toString())
        }

        private fun convertToDouble(str: String):Double?{
            return try {
                str.toDouble()
            }catch (e : NumberFormatException){
                null
            }
        }

        companion object{
            private const val STATE_RESULT = "state_result"
        }
    }


