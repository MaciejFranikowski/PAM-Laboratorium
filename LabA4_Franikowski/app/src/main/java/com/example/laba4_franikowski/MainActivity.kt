package com.example.laba4_franikowski

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var contactNameTextView: TextView
    private lateinit var smsBodyEditText: EditText
    private lateinit var longitudeEditText: EditText
    private lateinit var latitudeEditText: EditText
    private lateinit var showContactsButton: Button
    private lateinit var extraResultTextView: TextView
    private lateinit var indexEditText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showContactsButton = findViewById(R.id.showContactsButton)
        contactNameTextView = findViewById(R.id.contactNameTextView)
        longitudeEditText = findViewById(R.id.longitudeEditText)
        latitudeEditText = findViewById(R.id.latitudeEditText)
        extraResultTextView = findViewById(R.id.extraResultTextView)
        indexEditText = findViewById(R.id.indexEditText)
    }

    fun showContacts(view: View){
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        }
        startActivityForResult(intent, 102)

    }

    fun showBluetooth(view: View){
        var bluetoothIntent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS)
        startActivity(bluetoothIntent)
    }

    fun showMap(view: View){
        val longitude = longitudeEditText.text.toString()
        val latitude = latitudeEditText.text.toString()
        val geoUri = "geo:$latitude,$longitude?z=11"
        var mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(geoUri))
        startActivity(mapIntent)
    }

    fun showExtraActivity(view: View){
        val index = indexEditText.text.toString()
        var extraIntent = Intent(this, ExtraActivity::class.java)
        val myBundle = Bundle()
        myBundle.putString("index", index)
        extraIntent.putExtras(myBundle)
        startActivityForResult(extraIntent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 101 && resultCode == Activity.RESULT_OK){
            val myResultBundle = data?.extras
            extraResultTextView.text = myResultBundle?.getString("Result")
        }

        if (requestCode == 102 && resultCode == Activity.RESULT_OK) {
            val contactUri: Uri? = data?.data
            val projection: Array<String> = arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER)
            if (contactUri != null) {
                contentResolver.query(contactUri, projection, null, null, null).use { cursor ->
                    if (cursor != null) {
                        if (cursor.moveToFirst()) {
                            val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                            val number = cursor.getString(numberIndex)
                            // Do something with the phone number
                            contactNameTextView.text = number
                        }
                    }
                }
            }
        }
    }
}