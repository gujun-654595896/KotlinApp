package com.gujun.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.gujun.common.arouter.media.jumpToMediaActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        jump.setOnClickListener { jumpToMediaActivity(this) }
    }
}
