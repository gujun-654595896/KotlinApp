package com.gujun.database.manager

import android.content.Context
import com.gujun.database.gen.DaoMaster
import com.gujun.database.gen.DaoSession

object DbManager {

    //注意密码的保密性
    private const val db_password = "123456"

    private lateinit var mDaoSession: DaoSession

    /**
     * 在App启动时进行初始化，因为涉及到数据库升级，只有此方法调用了才进行数据库升级操作，所以尽早处理
     */
    fun initDb(context: Context) {
        val devOpenHelper = MigrationOpenHelper(context, "test.db", null)
        //使用加密可写的数据库,需要加入SQLCipher
        //异常：org.greenrobot.greendao.DaoException: Using an encrypted database requires SQLCipher
        val mDaoMaster = DaoMaster(devOpenHelper.getEncryptedWritableDb(db_password))
        mDaoSession = mDaoMaster.newSession()
    }

    fun getDaoSession(): DaoSession {
        return mDaoSession
    }

}