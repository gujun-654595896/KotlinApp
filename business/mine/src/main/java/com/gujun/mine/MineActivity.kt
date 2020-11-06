package com.gujun.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gujun.common.arouter.home.jumpToHomeActivity
import kotlinx.android.synthetic.main.activity_main.*

@Route(path = "/mine/MineActivity")
class MineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        jump.setOnClickListener { jumpToHomeActivity(this) }
    }
}
