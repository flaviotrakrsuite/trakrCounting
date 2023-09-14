package com.trakrsuite.trakrcounting

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val textView = findViewById<TextView>(R.id.new_signup)
        val fullText = "Don't have an account? Sign Up"
        val spannableText = SpannableString(fullText)

        val whiteColor = Color.WHITE
        spannableText.setSpan(
            ForegroundColorSpan(whiteColor),
            0,
            22,
            0
        )

        val greenColor = Color.parseColor("#37AD41")
        spannableText.setSpan(
            ForegroundColorSpan(greenColor),
            23,
            spannableText.length,
            0
        )

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val websiteUrl = getString(R.string.website_sign_up)
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
                ds.color = Color.parseColor("#37AD41")
            }
        }

        spannableText.setSpan(clickableSpan, 23, fullText.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        textView.text = spannableText
        textView.movementMethod = LinkMovementMethod.getInstance()
    }

    fun openWebsite(view: View) {
        val websiteUrl = getString(R.string.website_forgot_id)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl))
        startActivity(intent)
    }

    fun goToSelectCompany(view: View) {
        val intent = Intent(this, SelectCompanyActivity::class.java)
        startActivity(intent)
    }
}