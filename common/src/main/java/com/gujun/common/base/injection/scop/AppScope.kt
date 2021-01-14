package com.gujun.common.base.injection.scop

import javax.inject.Singleton

/**
 *    author : gujun
 *    date   : 2021/1/11 16:23
 *    desc   : Application级别的作用域注解
 */
@Singleton
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope