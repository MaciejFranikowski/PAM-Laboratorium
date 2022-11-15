package com.example.laba5_franikowski;
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsManager
import android.telephony.SmsMessage
import android.widget.Toast


class IncomingSmsReceiver:BroadcastReceiver() {

    private val SMS_REC_ACTION = "android.provider.Telephony.SMS_RECEIVED"
    private lateinit var smsManager : SmsManager;

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent!!.action.equals(SMS_REC_ACTION)) {
            val sb = StringBuilder()
            var smsAddress = ""
            val bundle = intent!!.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                for (pdu in pdus!!) {
                    val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                    smsAddress = smsMessage.displayOriginatingAddress
                    sb.append(smsMessage.displayMessageBody)
                }
            }
            val stringValue = sb.toString().trim()

            if(stringValue.startsWith("PILNE")){
                Toast.makeText(
                    context, stringValue, Toast.LENGTH_LONG
                ).show()
            } else {
                try {
                    if (stringValue.toInt() < 10)
                        sendSms(context, smsAddress, stringValue.toInt().plus(1).toString())
                } catch (_: java.lang.Exception) {
                }
            }
        }
    }
    private fun sendSms(context:Context?, address:String, body:String) {
        this.smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(address, null, body, null, null)
    }
}