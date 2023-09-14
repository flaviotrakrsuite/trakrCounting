package com.trakrsuite.trakrcounting

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class SelectCompanyActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)

        setupBackButton()

        val spinner = findViewById<Spinner>(R.id.spinner_select_company)
        val options = arrayOf("Pick Organization", "Aimco #2104", "Tomken #2603", "Trunk #95")
        val adapter = ArrayAdapter<String>(this, R.layout.custom_spinner_item, options)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                if (position == 0) {
                } else {
                    val selectedOption = options[position]
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
            }
        }

    }
}