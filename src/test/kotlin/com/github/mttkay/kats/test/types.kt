package com.github.mttkay.kats.test

typealias A = Int
typealias B = String
typealias C = Boolean

val a: A = 42

fun f(a: A): B = a.toString()
fun g(b: B): C = b.toBoolean()
