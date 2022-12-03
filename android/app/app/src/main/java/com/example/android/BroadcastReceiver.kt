package com.example.android

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import java.util.*

class BroadcastReceiver {
    fun onReceive(context: Context?, intent: Intent?) {
        //수신한 액션 처리 코드
        if ("android.provider.Telephony.SMS_RECEIVED".equals(intent!!.getAction())) {
            Log.d("onReceive()","문자가 수신되었습니다");

            // SMS 메시지를 파싱합니다.
            val bundle = intent!!.extras
            val messages = bundle!!["pdus"] as Array<Any>?
            val smsMessage: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(messages!!.size)

            for (i in messages!!.indices) {
                smsMessage[i] = SmsMessage.createFromPdu(messages!![i] as ByteArray)
            }

            val curDate = Date(smsMessage[0]!!.getTimestampMillis())
            Log.d("문자 수신 시간", curDate.toString())

            val origNumber: String = smsMessage[0]!!.originatingAddress.toString()
            val message: String = smsMessage[0]!!.getMessageBody().toString()
            Log.d("문자 내용", "발신자 : $origNumber, 내용 : $message")
        }
    }

    private fun getIncomingMessage(aObject: Any, bundle: Bundle): SmsMessage? {
        val currentSMS: SmsMessage
        currentSMS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            SmsMessage.createFromPdu(aObject as ByteArray, format)
        } else {
            SmsMessage.createFromPdu(aObject as ByteArray)
        }
        return currentSMS
    }

}