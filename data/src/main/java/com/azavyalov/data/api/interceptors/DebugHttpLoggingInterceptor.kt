package com.azavyalov.data.api.interceptors

import android.util.Log
import com.azavyalov.data.BuildConfig
import com.azavyalov.data.extensions.TAG
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

/** Интерцептор логирования HTTP запросов в сеть */
class DebugHttpLoggingInterceptor : Interceptor {

    /** Interceptor логирования, которому делегируются вызовы */
    private val delegate = HttpLoggingInterceptor()

    init {
        delegate.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (chain.request().body?.contentSizeInKb() ?: 0L > 1024L) {
            Log.w(TAG, "Контент запроса ${chain.request().url} слишком большой. Запрос не будет залогирован")
            return chain.proceed(chain.request())
        }
        return delegate.intercept(chain)
    }

    /** Возвращает размер контента в кб */
    private fun RequestBody.contentSizeInKb(): Long =
        this.contentLength() / 1024
}