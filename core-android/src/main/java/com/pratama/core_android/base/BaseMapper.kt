package com.pratama.core_android.base

interface BaseMapper<M, D> {
    fun mapToDomain(model: M): D
    fun mapToModel(domain: D): M
}