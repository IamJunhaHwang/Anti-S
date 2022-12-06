package com.example.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import java.util.*


class MyReceiver : BroadcastReceiver() {
    private val TAG = "SMSReceiver"

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called")

        if(intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            val bundle = intent?.extras
            val messages = smsMessageParse(bundle!!)

            if(messages?.size!! > 0){
                val content = messages[0]?.messageBody.toString()
                val date = Date(messages[0]!!.timestampMillis).toString()
                val sender = messages[0]?.displayOriginatingAddress.toString()

                Log.d("문자 내용", content)
                Log.d("송신자 번호", sender)
                Log.d("수신 시간", date)

                val intent = Intent(this, Fragment3:: class.java)

                bundle1.putString("송신자번호", sender)
                val bundle2 = Bundle()
                bundle2.putString("수신시간", date)
                val bundle3 = Bundle()
                bundle3.putString("문자내용", content)

                val passBundleFragment3 = PassBundleFragment()


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