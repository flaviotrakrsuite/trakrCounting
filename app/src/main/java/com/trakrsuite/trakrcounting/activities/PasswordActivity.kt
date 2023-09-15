package com.trakrsuite.trakrcounting.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.trakrsuite.trakrcounting.R


class PasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)

        setupBackButton()

        val passwordInputLayout = findViewById<TextInputLayout>(R.id.passwordInputLayout)
        val passwordEditText = findViewById<TextInputEditText>(R.id.passwordEditText)
        val selectionStart = passwordEditText.selectionStart
        val selectionEnd = passwordEditText.selectionEnd

        passwordInputLayout.setEndIconOnClickListener {
            val isPasswordVisible = passwordEditText.transformationMethod == PasswordTransformationMethod.getInstance()

            if (isPasswordVisible) {
                passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                passwordInputLayout.endIconDrawable = getDrawable(R.drawable.close_eye)
            } else {
                passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
                passwordInputLayout.endIconDrawable = getDrawable(R.drawable.open_eye)
            }

            passwordEditText.setSelection(selectionStart, selectionEnd)
        }

    }

    fun openWebsite(view: View) {
        val websiteUrl = getString(R.string.website_forgot_password)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
        startActivity(intent)
    }
}