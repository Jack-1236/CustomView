package com.android.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.android.customview.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { DataBindingUtil.setContentView(this, R.layout.activity_main) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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