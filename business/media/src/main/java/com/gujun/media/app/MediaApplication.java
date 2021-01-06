package com.gujun.media.app;

import android.app.Application;
import android.util.Log;

import com.gujun.common.base.IApplication;

/**
 * author : gujun
 * date   : 2021/1/5 13:14
 * desc   : 相关的初始化操作在此页面,
 * 此页面的调用不是直接调用的，而是通过字节码插桩调用的
 */
public class MediaApplication implements IApplication {

    @Override
    public void init(Application application) {
        Log.e("1111111111111111", "init:  + MediaApplication"+(application == null));
    }

}
