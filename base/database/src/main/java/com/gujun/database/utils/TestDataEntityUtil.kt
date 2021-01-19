package com.gujun.database.utils

import com.gujun.database.entity.TestDataEntity
import com.gujun.database.manager.DbManager

class TestDataEntityUtil private constructor() {

    companion object {

        private var instance: TestDataEntityUtil? = null

        fun getInstance(): TestDataEntityUtil {
            if (instance == null) {
                instance = TestDataEntityUtil()
            }
            return instance!!
        }
    }

    fun add(data: TestDataEntity): Boolean {
        return try {
            DbManager.getDaoSession().testDataEntityDao.insertOrReplace(data)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun queryAll(): List<TestDataEntity> {
        return DbManager.getDaoSession().testDataEntityDao.loadAll()
    }

    fun deleteById(id: Long): Boolean {
        return try {
            DbManager.getDaoSession().testDataEntityDao.deleteByKey(id)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun clear(): Boolean {
        return try {
            DbManager.getDaoSession().testDataEntityDao.deleteAll()
            true
        } catch (e: Exception) {
            false
        }
    }

}