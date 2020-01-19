package com.example.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import es.dmoral.toasty.Toasty

class EventHandle : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toasty.success(context,"Da nhan su kien").show()
    }
}
