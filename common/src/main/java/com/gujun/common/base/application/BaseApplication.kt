package com.gujun.common.base.application

import android.app.Application
import com.gujun.common.base.injection.component.DaggerAppComponent
import com.gujun.common.base.injection.module.AppModule
import com.gujun.database.manager.DbManager
import com.gujun.keystore.KeyStoreUtil

/**
 *    author : gujun
 *    date   : 2021/1/11 11:41
 *    desc   :
 */
open class BaseApplication : Application() {

    lateinit var appComponent: DaggerAppComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
        initDatabase()
    }

    private fun initInjection() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build() as DaggerAppComponent
    }

    private fun initDatabase() {
        DbManager.initDb(applicationContext, KeyStoreUtil.getInstance().getDataBaseEncryptionKey())
    }
}