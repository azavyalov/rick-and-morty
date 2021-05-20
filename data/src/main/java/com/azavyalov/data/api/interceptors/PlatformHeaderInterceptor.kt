package com.azavyalov.data.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/** Интерцептор, добавляющий платформу android в заголовок */
class PlatformHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("X-Platform", "android")
            .build()
        return chain.proceed(request)
    }
}