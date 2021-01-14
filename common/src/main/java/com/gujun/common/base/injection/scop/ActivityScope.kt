package com.gujun.common.base.injection.scop

import javax.inject.Scope

/**
 *    author : gujun
 *    date   : 2021/1/11 16:23
 *    desc   : Activity级别的作用域注解
 */
@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope