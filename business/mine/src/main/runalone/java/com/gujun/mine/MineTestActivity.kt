package com.gujun.mine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gujun.common.arouter.home.jumpToHomeActivity
import kotlinx.android.synthetic.main.mine_activity_main.*

class MineTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mine_activity_main)
        jump.setOnClickListener { jumpToHomeActivity(this) }
    }
}
