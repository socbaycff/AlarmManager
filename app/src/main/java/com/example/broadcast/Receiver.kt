package com.example.broadcast

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import es.dmoral.toasty.Toasty

class Receiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        if (context != null && intent != null) {
            Toasty.error(context!!,"Da chay broadcase").show() // show toast
            val stringExtra = intent.getStringExtra("id") // lay id

            // chuan di pending
            val intentMoActivity = Intent(context, MainActivity::class.java)
            val pending = PendingIntent.getActivity(context, 0, intentMoActivity, 0)
            // chuan bi pending cho su kien
            val intentSK = Intent(context, EventHandle::class.java)
            val pendingSK = PendingIntent.getBroadcast(context, 0, intentSK, 0)


            // lay anh
            val bitmap = BitmapFactory.decodeResource(
                context.resources,
                R.drawable.auto_black_side_view_117647_1920x1080
            )
            val action =
                NotificationCompat.Action(R.drawable.ic_launcher_foreground, "Nut", pendingSK)


            // tao noti
            val notification = NotificationCompat.Builder(context, stringExtra)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(bitmap)
                .addAction(action)
                .setContentText("Noi dung")
                .setContentTitle("Tua de")
                .setContentIntent(pending)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText("dqwidqwdiuqwidiqwindqwudiqwnudiqwdiqdiuquwdnqwidiqwniudquiwduiqi, dqwidqwdiuqwidiqwindqwudiqwnudiqwdiqdiuquwdnqwidiqwniudquiwduiqidqwidqwdiuqwidiqwindqwudiqwnudiqwdiqdiuquwdnqwidiqwniudquiwduiqi")
                )
                .setAutoCancel(true)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .build()

            context.getSystemService(NotificationManager::class.java).notify(0 , notification) // gui noti


        }





    }
}