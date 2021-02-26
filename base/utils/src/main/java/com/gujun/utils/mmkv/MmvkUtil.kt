package com.gujun.utils.mmkv

import android.app.Application
import android.content.SharedPreferences
import android.os.Parcelable
import com.tencent.mmkv.MMKV

/**
 *    author : gujun
 *    date   : 2021/2/26 10:07
 *    desc   : 替代SharePreference的工具，腾讯开发的三方框架
 */
class MmvkUtil private constructor() {

    private var mmkv = MMKV.defaultMMKV()

    companion object {

        @Volatile
        private var instance: MmvkUtil? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MmvkUtil().also { instance = it }
        }

        fun init(app: Application) {
            MMKV.initialize(app)
        }
    }

    /**
     * 将SharedPreferences的旧数据导入到MMKV中
     */
    fun importSharePreference(sp: SharedPreferences) {
        mmkv.importFromSharedPreferences(sp)
    }

    fun encode(key: String, value: Any) {
        when (value) {
            is String -> {
                mmkv.encode(key, value)
            }
            is Int -> {
                mmkv.encode(key, value)
            }
            is Boolean -> {
                mmkv.encode(key, value)
            }
            is Float -> {
                mmkv.encode(key, value)
            }
            is Long -> {
                mmkv.encode(key, value)
            }
            is Double -> {
                mmkv.encode(key, value)
            }
            is ByteArray -> {
                mmkv.encode(key, value)
            }
            is Parcelable -> {
                mmkv.encode(key, value)
            }
            else -> {
                mmkv.encode(key, value.toString())
            }
        }
    }

    fun encodeSet(key: String, value: Set<String>) {
        mmkv.encode(key, value)
    }

    fun decodeInt(key: String?): Int? {
        return mmkv.decodeInt(key, 0)
    }

    fun decodeDouble(key: String?): Double? {
        return mmkv.decodeDouble(key, 0.00)
    }

    fun decodeLong(key: String?): Long? {
        return mmkv.decodeLong(key, 0L)
    }

    fun decodeBoolean(key: String?): Boolean? {
        return mmkv.decodeBool(key, false)
    }

    fun decodeFloat(key: String?): Float? {
        return mmkv.decodeFloat(key, 0f)
    }

    fun decodeBytes(key: String?): ByteArray? {
        return mmkv.decodeBytes(key)
    }

    fun decodeString(key: String?): String? {
        return mmkv.decodeString(key, "")
    }

    fun decodeStringSet(key: String?): Set<String?>? {
        return mmkv.decodeStringSet(key, emptySet())
    }

    fun decodeParcelable(key: String?): Parcelable? {
        return mmkv.decodeParcelable(key, null)
    }

    /**
     * 移除某个key对
     *
     * @param key
     */
    fun removeKey(key: String?) {
        mmkv.removeValueForKey(key)
    }

    /**
     * 清除所有key
     */
    fun clearAll() {
        mmkv.clearAll()
    }

}