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
            val smsAddress = ""
            val bundle = intent!!.extras
            if (bundle != null) {
                val pdus = bundle["pdus"] as Array<Any>?
                for (pdu in pdus!!) {
                    val smsMessage: SmsMessage = SmsMessage.createFromPdu(pdu as ByteArray)
                    val smsAddress = smsMessage.displayOriginatingAddress
                    sb.append("body - " + smsMessage.displayMessageBody)
                }
            }
            if(smsAddress == "+48721821410" || smsAddress=="+48669290979" || smsAddress=="666606362"){
                sendSms(context, smsAddress)
            }
            Toast.makeText(
                context, "SMS RECEIVED - "
                        + sb.toString(), Toast.LENGTH_LONG
            ).show()
        }
    }
    private fun sendSms(context:Context?, address:String) {
        this.smsManager = SmsManager.getDefault()
        smsManager.sendTextMessage(address, null, "Response", null, null)
    }
}