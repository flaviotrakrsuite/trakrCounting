package com.trakrsuite.trakrcounting.activities
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.trakrsuite.trakrcounting.R

open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun setupBackButton() {
        val backButton = findViewById<ImageButton>(R.id.imageButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }

    fun showErrorDialog(title: String, message: String, button: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(button){ dialogInterface: DialogInterface, _: Int ->
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
