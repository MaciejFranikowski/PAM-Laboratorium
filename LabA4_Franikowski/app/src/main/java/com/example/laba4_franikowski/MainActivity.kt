package com.example.laba4_franikowski

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.provider.ContactsContract
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var smsNumberEditText: EditText
    private lateinit var smsBodyEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var latitudeEditText: EditText
    private lateinit var showContactsButton: Button
    private lateinit var extraResultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContactsButton = findViewById(R.id.showContactsButton)
        smsNumberEditText = findViewById(R.id.smsNumberEditText)
        smsBodyEditText = findViewById(R.id.smsBodyEditText)
        longitudeEditText = findViewById(R.id.longitudeEditText)
        latitudeEditText = findViewById(R.id.latitudeEditText)
        extraResultTextView = findViewById(R.id.extraResultTextView)
    }

    fun showContacts(view: View){
        val contactsUri = ContactsContract.Contacts.CONTENT_URI
        var contactsIntent = Intent(Intent.ACTION_VIEW, contactsUri)
        startActivity(contactsIntent)
    }

    fun writeSms(view: View){
        val textBody = smsBodyEditText.text.toString()
        val number = smsNumberEditText.text.toString()
        val numberUri = "smsto:$number"
        var smsIntent = Intent(Intent.ACTION_SENDTO, Uri.parse(numberUri))
        smsIntent.putExtra("sms_body", textBody)
        startActivity(smsIntent)
    }

    fun showMap(view: View){
        val longitude = longitudeEditText.text.toString()
        val latitude = latitudeEditText.text.toString()
        val geoUri = "geo:$latitude,$longitude&z=15"
        var mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        startActivity(mapIntent)
    }

    fun showExtraActivity(view: View){
        val longitude = longitudeEditText.text.toString()
        val latitude = latitudeEditText.text.toString()
        var extraIntent = Intent(this, ExtraActivity::class.java)
        val myBundle = Bundle()
        myBundle.putString("longitude", longitude)
        myBundle.putString("latitude", latitude)
        extraIntent.putExtras(myBundle)
        startActivityForResult(extraIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try{
            if(requestCode == 101 && resultCode == Activity.RESULT_OK){
                val myResultBundle = data?.extras
                extraResultTextView.text = myResultBundle?.getString("Result")
            }

        } catch(e: Exception){

        }
    }
}