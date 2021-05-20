package com.azavyalov.data.extensions

/** Тег для логирования */
val Any.TAG: String
    get() = this::class.java.run {
        if (isAnonymousClass) {
            enclosingClass?.simpleName ?: simpleName
        } else {
            simpleName
        }
    }