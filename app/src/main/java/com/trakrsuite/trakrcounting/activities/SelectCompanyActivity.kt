package com.trakrsuite.trakrcounting.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText
import com.trakrsuite.trakrcounting.R
import com.trakrsuite.trakrcounting.model.UserValidationRequest
import com.trakrsuite.trakrcounting.network.ApiClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SelectCompanyActivity : BaseActivity() {

    private lateinit var spinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_company)

        setupBackButton()

        val imageButton = findViewById<Button>(R.id.button_next)

        spinner = findViewById<Spinner>(R.id.spinner_select_company)

        val locationNames = intent.getStringArrayListExtra("locations")

        if (locationNames != null) {
            locationNames.add(0, "Pick Organization")

            val adapter = ArrayAdapter(this, R.layout.custom_spinner_item, locationNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                    if (position == 0) {
                        imageButton.visibility = View.INVISIBLE
                        // El usuario seleccionó "Seleccionar Ubicación", puedes realizar acciones aquí si es necesario.
                    } else {
                        imageButton.visibility = View.VISIBLE
                        val selectedLocationName = locationNames[position]

                    }
                }

                override fun onNothingSelected(parentView: AdapterView<*>) {
                }
            }
        }

    }

    fun goToPassword(view: View) {
        val intent = Intent(this@SelectCompanyActivity, PasswordActivity::class.java)
        startActivity(intent)
    }
}