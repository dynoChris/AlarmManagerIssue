package com.oliverstudio.alarmmanagerissue

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_alarm_btn.setOnClickListener {
            startAlarm(alarm_time_et.text.toString().toInt());
            Toast.makeText(this, "Alarm set", Toast.LENGTH_SHORT).show()
        }

    }

    private fun startAlarm(seconds: Int) {
        val launchTimeInMillis = computeLaunchTime(seconds)
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, MyAlarm::class.java)
        intent.putExtra("time", seconds)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, launchTimeInMillis, pendingIntent)
    }

    private fun computeLaunchTime(seconds: Int): Long {
        val currentDate = Calendar.getInstance()
        currentDate.add(Calendar.SECOND, seconds)
        return currentDate.timeInMillis
    }
}