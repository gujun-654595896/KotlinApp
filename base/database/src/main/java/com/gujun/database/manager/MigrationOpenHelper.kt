package com.gujun.database.manager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.github.yuweiguocn.library.greendao.MigrationHelper
import com.gujun.database.gen.DaoMaster
import com.gujun.database.gen.TestDataEntityDao
import org.greenrobot.greendao.database.Database

/**
 *    author : gujun
 *    date   : 2021/1/18 16:16
 *    desc   : 数据库升级防止数据被清空的工具
 */
class MigrationOpenHelper(
    val mContext: Context,
    val name: String,
    val factory: SQLiteDatabase.CursorFactory? = null
) :
    DaoMaster.OpenHelper(mContext, name, factory) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        MigrationHelper.migrate(db, object : MigrationHelper.ReCreateAllTableListener {
            override fun onDropAllTables(db: Database?, ifExists: Boolean) {
                DaoMaster.dropAllTables(db, ifExists)
            }

            override fun onCreateAllTables(db: Database?, ifNotExists: Boolean) {
                DaoMaster.createAllTables(db, ifNotExists)
            }

        }, TestDataEntityDao::class.java)//此处可以添加多个《需要使用旧版数据》的类
    }

}