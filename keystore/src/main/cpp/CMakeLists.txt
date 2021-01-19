#指定cmake最小支持版本
cmake_minimum_required(VERSION 3.4.1)

#添加一个库，根据native-lib.cpp文件编译一个native-lib的动态库
add_library(key-lib
        SHARED
        key-lib.cpp)

#查找系统库，这里查找的是系统日志库，并赋值给变量log-lib，可以省略
find_library( log-lib
        log )

#设置依赖的库（第一个参数必须为目标模块，顺序不能换）
target_link_libraries( key-lib
        ${log-lib} )