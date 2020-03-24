package io.chillout.smsrecived

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class SMSReciver :BroadcastReceiver(){
    val SMS_RECIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED"
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(SMS_RECIVED_ACTION)){
            val bundle : Bundle = intent?.extras!!
            //check if bundle not equals to null if done in java
            //here we done that already through not-null assertion
            //don't know WTF is that @{puds}
            Log.d("From Receiver","Message Received")
            Toast.makeText(context,"Message Received",Toast.LENGTH_SHORT).show()
            //We cannot use this Receiver if any other receiver has high priority than our app
            //revoke the SMS permissions of other SMS App and grant SMS Permission for Our App
            //We cannot Send Dialogs from Broadcast Receiver but we can Send Activity as Dialog
            //Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK
            context?.startActivity(Intent(context,AlertActivity::class.java).addFlags(FLAG_ACTIVITY_NEW_TASK))
        }
    }
}