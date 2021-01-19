package com.gujun.keystore

/**
 *    author : gujun
 *    date   : 2021/1/19 11:29
 *    desc   : 获取本地密钥工具类
 */
class KeyStoreUtil private constructor() {

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("key-lib")
        }

        private var keyStoreUtil: KeyStoreUtil? = null

        fun getInstance(): KeyStoreUtil {
            if (keyStoreUtil == null) {
                keyStoreUtil =
                    KeyStoreUtil()
            }
            return keyStoreUtil!!
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    /**
     * 获取本地密钥
     * type:
     * 0:数据库密钥
     */
    private external fun getKey(type: Int): String

    /**
     * 获取数据库加密KEY
     */
    fun getDataBaseEncryptionKey(): String {
        return getKey(0)
    }
}