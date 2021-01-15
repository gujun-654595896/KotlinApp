package com.gujun.kotlinapp.mvp.modelImpl

import com.gujun.kotlinapp.mvp.TestMvpContract
import com.gujun.network.Requester
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/**
 *    author : gujun
 *    date   : 2021/1/14 10:19
 *    desc   : 测试数据处理类
 */
//CoroutineScope by MainScope()为添加协程作用域
class TestMvpModelImpl : TestMvpContract.Model, CoroutineScope by MainScope() {

    override fun getData(callback: TestMvpModelImplCallBack) {
        launch {
            try {
                val requestMap = HashMap<String, String>()
                requestMap["ip"] = "223.5.5.5"
                //3、******  retrofit2.6.0以后支持suspend挂起修饰，所以能返回真实对象，不再调用await()函数 ******
                val data = Requester.apiService().getDataAsync(requestMap)
                callback?.onResult(data.toString())
            } catch (e: Exception) {
                callback?.onResult("error:${e.message} ")
            }
        }
    }

    override fun onDestroy() {
        //此处是关键：取消协程
        cancel()
    }

    interface TestMvpModelImplCallBack {
        fun onResult(data: String)
    }
}