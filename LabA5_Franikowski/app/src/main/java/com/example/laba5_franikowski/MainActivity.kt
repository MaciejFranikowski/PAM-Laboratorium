package com.example.laba5_franikowski

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.PhoneStateListener
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var textView1: TextView = findViewById(R.id.textView1)

        val permission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_PHONE_STATE
        )

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                1
            )
        }

        var telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager

        textView1.text = "CallState: ".plus(telephonyManager.callState)
            .plus(", PhoneType: ").plus(telephonyManager.phoneType)
            .plus(", NetworkType: ").plus(telephonyManager.networkType)
    }

    override fun onStart() {
        super.onStart()

        var textView2: TextView = findViewById(R.id.textView2)
        val telMgr = this.getSystemService(
            Context.TELEPHONY_SERVICE
        ) as TelephonyManager
        val phoneStateListener: PhoneStateListener = object : PhoneStateListener() {
            @SuppressLint("MissingPermission")
            override fun onCallStateChanged(
                state: Int, incomingNumber: String
            ) {
                textView2.text = telMgr.dataNetworkType.toString()
            }
        }
        telMgr.listen(
            phoneStateListener,
            PhoneStateListener.LISTEN_CALL_STATE
        )
//        val telephonyOverview: String = this.getTelephonyOverview(telMgr)
//        this.telMgrOutput.setText(telephonyOverview)
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i("TAG", "Permission has been denied by user")
                } else {
                    Log.i("TAG", "Permission has been granted by user")
                }
            }
        }
    }
}