package com.myapps.dogsrecycler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private var loginIsValid = false
    private var passwordIsValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addTextChangeListener(login)
        addTextChangeListener(password)
        signIn.setOnClickListener {
            Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addTextChangeListener(editText: EditText) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (editText.id == R.id.login)
                    validateLogin(s)
                else
                    validatePassword(s)
                signIn.isEnabled = loginIsValid && passwordIsValid
            }
        })
    }

    private fun validateLogin(s: CharSequence?) {
        if (s != null && s.matches("^[\\w]([\\w-.@_]?[\\w])*\$".toRegex())) {
            loginIsValid = true
        } else {
            login.error = resources.getString(R.string.login_error)
            loginIsValid = false
        }
    }

    private fun validatePassword(s: CharSequence?) {
        if (s != null) {
            val isEnoughLength = s.matches("[\\w-.@_&?/%()$#*]{8,30}".toRegex())
            val hasCapitalLetter = s.contains("[A-Z|А-Я]+".toRegex())
            val hasNumeric = s.contains("[1-9]+".toRegex())
            if (isEnoughLength) {
                if (hasCapitalLetter) {
                    if (hasNumeric) {
                        passwordIsValid = true
                    }
                    else{
                        password.error = resources.getString(R.string.password_error_numeric)
                        passwordIsValid = false
                    }
                } else {
                    password.error = resources.getString(R.string.password_error_capital_letter)
                    passwordIsValid = false
                }
            } else {
                password.error = resources.getString(R.string.password_error_length)
                passwordIsValid = false
            }
        }
    }

}
