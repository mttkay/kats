package com.github.mttkay.kats.ext.collection

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.predef.*

fun <T> Collection<T>.fold(m: Monoid<T>): T =
    fold(m.empty) { a, b -> m.combine(a, b) }

fun Collection<Int>.fold(): Int = fold(AddIntMonoid)
fun Collection<Long>.fold(): Long = fold(AddLongMonoid)
fun Collection<Float>.fold(): Float = fold(AddFloatMonoid)
fun Collection<Double>.fold(): Double = fold(AddDoubleMonoid)
fun Collection<String>.fold(): String = fold(AddStringMonoid)

fun Collection<Int?>.fold(): Int? = fold(AddNullableIntMonoid)
fun Collection<Long?>.fold(): Long? = fold(AddNullableLongMonoid)
fun Collection<Float?>.fold(): Float? = fold(AddNullableFloatMonoid)
fun Collection<Double?>.fold(): Double? = fold(AddNullableDoubleMonoid)
//fun Collection<String?>.fold(): String? = fold(AddNullableStringMonoid)
