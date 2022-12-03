package com.example.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log

class MyReceiver : BroadcastReceiver() {
    private val TAG = "SMSReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called")
        if(intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            val bundle = intent?.extras
            val messages = smsMessageParse(bundle!!)

            if(messages?.size!! > 0){
                val content = messages[0]?.messageBody.toString()
                Log.d("인증번호 추출 ", content)
            }
        }
    }

    fun smsMessageParse(bundle: Bundle): Array<SmsMessage?>? {
        val objs = bundle["pdus"] as Array<*>?
        val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(objs!!.size)
        for (i in objs.indices) {
            messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
        }
        return messages
    }

}