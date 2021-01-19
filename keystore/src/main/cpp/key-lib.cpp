#include <jni.h>
#include <string>

const char *KEY_DATABASE_ENCRYPTION = "123456"; //数据库加密的KEY

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gujun_keystore_KeyStoreUtil_stringFromJNI(JNIEnv *env, jobject thiz) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_gujun_keystore_KeyStoreUtil_getKey(JNIEnv *env, jobject thiz, jint type) {
    switch (type) {
        case 0:
            return env->NewStringUTF(KEY_DATABASE_ENCRYPTION);
    }
}