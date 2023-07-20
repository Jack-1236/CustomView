package com.android

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.graphics.scaleMatrix
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
    //    testProgress()
        val  holder1 = PropertyValuesHolder.ofFloat("scaleX", -2f);
        val holder2 = PropertyValuesHolder.ofFloat("scaleY", -2f);
        val holder3 = PropertyValuesHolder.ofFloat("alpha", 1f);

        val  animator = ObjectAnimator.ofPropertyValuesHolder(binding.mainProgress, holder1, holder2, holder3)

        binding.mainProgress.setOnClickListener {
          binding.mainProgress.animation.apply {
              scaleMatrix()
              start()
          }
        }
    }

    private fun testProgress() {
        val animator = ValueAnimator.ofFloat(0f, 65f)
        animator.duration = 1000

        animator.addUpdateListener { valueAnimator ->
            val animatedValue = valueAnimator.animatedValue as Float
            // 在这里更新自定义View的属性值，例如：
        }

        animator.startDelay = (2500)
        animator.start()

    }

    private fun testBattery() {
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