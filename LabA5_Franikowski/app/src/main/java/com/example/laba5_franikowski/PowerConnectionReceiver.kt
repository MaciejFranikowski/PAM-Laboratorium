package com.example.laba5_franikowski;
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class PowerConnectionReceiver:BroadcastReceiver() {

    private val ACTION_POWER_CONNECTED = "android.intent.action.ACTION_POWER_CONNECTED"
    private val ACTION_POWER_DISCONNECTED = "android.intent.action.ACTION_POWER_DISCONNECTED"
    private val BATTERY_LOW = "android.intent.action.BATTERY_LOW"
    private val BATTERY_OKAY = "android.intent.action.BATTERY_OKAY"

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(ACTION_POWER_CONNECTED)) {
            Toast.makeText(context, "Power connected", Toast.LENGTH_LONG).show()
        }

        if (intent!!.action.equals(ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "Power disconnected", Toast.LENGTH_LONG).show()
        }

        if (intent!!.action.equals(BATTERY_LOW)) {
            Toast.makeText(context, "Power low", Toast.LENGTH_LONG).show()
        }

        if (intent!!.action.equals(BATTERY_OKAY)) {
            Toast.makeText(context, "Power ok", Toast.LENGTH_LONG).show()
        }
    }
}