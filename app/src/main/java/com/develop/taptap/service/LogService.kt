package com.develop.taptap.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}