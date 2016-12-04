package com.github.mttkay.kats.ext.collection

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.predef.*

fun <T> Iterable<T>.fold(m: Monoid<T>): T =
    fold(m.empty) { a, b -> m.combine(a, b) }

fun Iterable<Int>.fold(): Int = fold(AddIntMonoid)
fun Iterable<Long>.fold(): Long = fold(AddLongMonoid)
fun Iterable<Float>.fold(): Float = fold(AddFloatMonoid)
fun Iterable<Double>.fold(): Double = fold(AddDoubleMonoid)
fun Iterable<String>.fold(): String = fold(AddStringMonoid)

fun Iterable<Int?>.fold(): Int? = fold(AddNullableIntMonoid)
fun Iterable<Long?>.fold(): Long? = fold(AddNullableLongMonoid)
fun Iterable<Float?>.fold(): Float? = fold(AddNullableFloatMonoid)
fun Iterable<Double?>.fold(): Double? = fold(AddNullableDoubleMonoid)
//fun Collection<String?>.fold(): String? = fold(AddNullableStringMonoid)

fun <A> Iterable<A>.liftList(): ListContext<A> = ListContext(this.toList())

fun <A, B> Iterable<Option<A>>.mapOption(f: (A) -> B): List<Option<B>> = this.map { it.map(f) }
inline fun <A, B> Iterable<A?>.mapNullable(f: (A) -> B): List<B?> = this.map { it?.let(f) }
