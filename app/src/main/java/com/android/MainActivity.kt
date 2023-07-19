package com.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.custom.views.R
import com.android.custom.views.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainBattery.isCharging = true
        binding.mainBattery.chargingColor = R.color.green

        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1..100)
                withContext(Dispatchers.Main) {
                    binding.mainBattery.batteryLevel = i
                    withContext(Dispatchers.IO) {
                        delay(300)
                    }
                }
        }


    }
}