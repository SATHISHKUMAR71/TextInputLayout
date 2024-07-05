package com.example.textinputfieldlayouts

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInputLayoutName = findViewById<TextInputLayout>(R.id.textInputLayout)
        val textInputLayoutPhone = findViewById<TextInputLayout>(R.id.textInputLayout2)
        val textInputLayoutPassword = findViewById<TextInputLayout>(R.id.textInputLayout3)
        val btn = findViewById<Button>(R.id.btn)
        setAddTextChangedListenerForLength(textInputLayoutName,3)
        setAddTextChangedListenerForLength(textInputLayoutPhone,7)
        setAddTextChangedListenerForLength(textInputLayoutPassword,8)
        btn.setOnClickListener{
            Intent(Intent.ACTION_MAIN).also {
                it.`package`="com.google.android.youtube"
                startActivity(it)
            }
        }
    }

    private fun setAddTextChangedListenerForLength(textInputLayout: TextInputLayout,minLength:Int){
        textInputLayout.editText?.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                val text = p0.toString()
                if (text.isNotEmpty() && text.length >= minLength) {
                    textInputLayout.error = null // Clear error if password is valid
                } else {
                    textInputLayout.editText?.onFocusChangeListener = View.OnFocusChangeListener{ v,hasFocus->
                        if(!hasFocus){
                            if(textInputLayout.editText?.text.toString().length < minLength){
                                textInputLayout.error = "Name Should Be atLeast $minLength Characters"
                            }

                        }
                    }
                }
            }
        })
    }
}