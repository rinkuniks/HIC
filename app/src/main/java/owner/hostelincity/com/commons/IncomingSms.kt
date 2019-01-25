package owner.hostelincity.com.commons

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import owner.hostelincity.com.interfaces.SmsListeners


class IncomingSms : BroadcastReceiver() {
    override fun onReceive(context: Context , intent: Intent) {
        val bundle = intent.extras

        try {
            if (bundle != null) {
                val pdusObj = bundle.get("pdus") as Array<Any>
                if (pdusObj != null) {
                    for (aPdusObj in pdusObj) {
                        val currentMessage = SmsMessage.createFromPdu(aPdusObj as ByteArray)

                        Log.d("came currentMessage=>" , currentMessage.toString() + "")
                        val phoneNumber = currentMessage.displayOriginatingAddress
                        //                        Boolean b = phoneNumber.endsWith("ITSMYT");
                        Log.d("came phoneNumber=>" , phoneNumber + "")
                        val message = currentMessage.displayMessageBody
                        intent.putExtra("massage" , message)
                        val b = message.startsWith("Dear User,")

                        mListener!!.messageReceived(message)//estra
                        try {
                            if (b) {
                                mListener!!.messageReceived(message)
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    companion object {

        private var mListener: SmsListeners? = null
        fun bindListener(listener: SmsListeners) {
            mListener = listener
        }
    }
}
