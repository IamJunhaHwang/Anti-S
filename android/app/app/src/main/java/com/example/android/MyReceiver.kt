package com.example.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.util.*


class MyReceiver : BroadcastReceiver() {
    private val TAG = "SMSReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called")

        if(intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            val bundle = intent?.extras
            val messages = parseSmsMessage(bundle!!)

            if(messages?.size!! > 0){
                val content = messages[0]?.messageBody.toString()
                val date = Date(messages[0]!!.timestampMillis)
                val sender = messages[0]?.displayOriginatingAddress.toString()

                Log.d("문자 내용", content)
                Log.d("송신자 번호", sender)
                Log.d("수신 시간", date.toString())

                sendToActivity(context, sender, content, date)
            }
        }
    }

    private fun parseSmsMessage(bundle: Bundle): Array<SmsMessage?>? {
        val objs = bundle["pdus"] as Array<*>?
        val messages = arrayOfNulls<SmsMessage>(objs!!.size)
        for (i in objs.indices) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }

    private fun sendToActivity(
        context: Context,
        sender: String,
        contents: String,
        receivedDate: Date
    ) {
        val intent = Intent(context, Fragment3::class.java)

        intent.addFlags(
            Intent.FLAG_ACTIVITY_NEW_TASK
                    or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    or Intent.FLAG_ACTIVITY_CLEAR_TOP
        )
        intent.putExtra("sender", sender)
        intent.putExtra("contents", contents)
        intent.putExtra("receivedDate", (receivedDate))
        context.startActivity(intent)
    }
}