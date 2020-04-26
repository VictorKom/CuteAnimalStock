package com.myapps.dogsrecycler

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
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
        if (s != null && s.matches("[\\w-.@_&?/%()$#*]{8,30}".toRegex())
            && s.contains("[A-Z|А-Я]+".toRegex()) && s.contains("[1-9]+".toRegex())) {
            passwordIsValid = true
        } else {
            password.error = resources.getString(R.string.password_error)
            passwordIsValid = false
        }
    }

}
