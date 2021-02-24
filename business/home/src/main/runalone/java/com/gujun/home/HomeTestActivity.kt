package com.gujun.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gujun.common.arouter.media.jumpToMediaActivity
import kotlinx.android.synthetic.main.home_activity_home.*

class HomeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity_home)
        jump.setOnClickListener { jumpToMediaActivity(this) }
    }
}
