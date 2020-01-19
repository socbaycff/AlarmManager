package com.example.broadcast

import android.app.*
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.icu.util.Calendar


class MainActivity : AppCompatActivity() {

    companion object Constant {

        val channelId = "idchannel1"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        taoKenh()
    }

    fun taoKenh() {
        val notificationChannel =
            NotificationChannel(channelId, "Kenh Bao thuc", NotificationManager.IMPORTANCE_HIGH)
        val notiService = getSystemService(NotificationManager::class.java)
        notiService.createNotificationChannel(notificationChannel)


    }


    fun giaoDienLayThoiGian(v: View) {

        // set thoi gian luc moi mo dong ho, thoi gian mac dinh
        val mcurrentTime =
            Calendar.getInstance() // lay tg hien tai tu Lich : Lay ngay thang nam, gio phut giay
        val gioMacDInh = mcurrentTime.get(Calendar.HOUR_OF_DAY) // lay gio hien tai tu calender tren
        val phutMacDinh = mcurrentTime.get(Calendar.MINUTE) // lay phut hien tai tu calender tren

        // tao cua so bao thuc
        val dialogBaoThuc = TimePickerDialog(this, { view, hourOfDay, minute ->

            val intent = Intent(this, Receiver::class.java)
            intent.putExtra("id", channelId)
            val pend = PendingIntent.getBroadcast(this, 0, intent, 0)


            // cach lay milis tu ket qua
            val tgHienTai = Calendar.getInstance() // lay ngay gio hien tai cua he thong
            tgHienTai.set(Calendar.HOUR_OF_DAY, hourOfDay) // set gio chon trong lich de len
            tgHienTai.set(Calendar.MINUTE, minute) //
            tgHienTai.set(Calendar.SECOND, 0)
            tgHienTai.set(Calendar.MILLISECOND, 0)

            // goi set bao thuc tu he thong
            val dichVuBaoThuc = getSystemService(AlarmManager::class.java)
            dichVuBaoThuc.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                tgHienTai.timeInMillis,
                pend
            )

        }, gioMacDInh, phutMacDinh, true)

        dialogBaoThuc.show() // hien cua so bao thuc
    }


}
