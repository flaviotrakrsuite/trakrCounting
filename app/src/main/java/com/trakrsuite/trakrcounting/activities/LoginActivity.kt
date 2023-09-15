package com.trakrsuite.trakrcounting.activities

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
import android.util.Log
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trakrsuite.trakrcounting.R
import com.trakrsuite.trakrcounting.model.Location
import com.trakrsuite.trakrcounting.model.UserValidationApiResponse
import com.trakrsuite.trakrcounting.model.UserValidationDataMultipleLocations
import com.trakrsuite.trakrcounting.model.UserValidationDataSingleLocation
import com.trakrsuite.trakrcounting.model.UserValidationRequest
import com.trakrsuite.trakrcounting.network.ApiClient
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity() {
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
        val inputLogin = findViewById<TextInputEditText>(R.id.input_login)
        val inputValue = inputLogin.text.toString().trim()

        if (inputValue.isNotEmpty()) {
            try {
                val request = UserValidationRequest(usrUsername = inputValue)
                val call: Call<ResponseBody> = ApiClient.apiService.validateUser(request)

                call.enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                        if (response.isSuccessful) {
                            try {
                                val responseBody = response.body()?.string()
                                if (responseBody != null) {
                                    val jsonResponse = JSONObject(responseBody)
                                    Log.d("DevLog", jsonResponse.toString())
                                    val dataObject = jsonResponse.optJSONObject("data")
                                    val hasDuplicates = dataObject?.optBoolean("hasDuplicates", false) ?: false

                                    if (hasDuplicates) {
                                        val locations = getLocations(jsonResponse)
                                        val locationNames = locations.map { it.locName }
                                        val intent = Intent(this@LoginActivity, SelectCompanyActivity::class.java)
                                        intent.putStringArrayListExtra("locations", ArrayList(locationNames))
                                        startActivity(intent)

                                    } else {
                                        val intent = Intent(this@LoginActivity, PasswordActivity::class.java)
                                        startActivity(intent)
                                    }
                                }
                            } catch (e: Exception) {

                            }
                        }
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        val title = "Error performing sign-in"
                        var message = "User was not found."
                        var button = "Try Again"
                        showErrorDialog(title, message, button)
                        Log.e("ApiError", "Error en la llamada a la API: ${t.message}", t)
                    }
                })


            }catch (e: Exception){
                Log.d("DevLog", e.toString())
            }
        }else{
            val title = "Error performing sign-in"
            var message = "User was not found."
            var button = "Try Again"
            showErrorDialog(title, message, button)
        }
    }

    private fun getLocations(jsonResponse: JSONObject): ArrayList<Location> {
        val dataObject = jsonResponse.optJSONObject("data")
        val locationsArray = dataObject?.optJSONArray("locations")
        val locationsList = ArrayList<Location>()

        if (locationsArray != null) {
            for (i in 0 until locationsArray.length()) {
                val locationObject = locationsArray.getJSONObject(i)
                val locID = locationObject.optInt("locID")
                val locName = locationObject.optString("locName")
                locationsList.add(Location(locID, locName))
            }
        }

        return locationsList
    }


}