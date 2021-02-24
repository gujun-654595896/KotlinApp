package com.gujun.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.gujun.common.arouter.mine.jumpToMineActivity
import kotlinx.android.synthetic.main.media_activity_media.*

@Route(path = "/media/MediaActivity")
class MediaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.media_activity_media)
        jump.setOnClickListener { jumpToMineActivity(this) }
    }
}
