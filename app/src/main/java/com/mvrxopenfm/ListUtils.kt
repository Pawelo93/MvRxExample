package com.mvrxopenfm

fun <T> List<T>.addOrRemove(element: T) = toMutableList().apply {
    if (contains(element))
        remove(element)
    else
        add(element)
}