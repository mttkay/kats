package com.github.mttkay.kats.ext.collection

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.map
import com.github.mttkay.kats.predef.*

fun <T> Iterable<T?>.fold(m: Monoid<T>): T =
    fold(m.empty) { a, b -> m.combine(a, b ?: m.empty) }

fun Iterable<Int?>.sum(): Int = fold(AddIntMonoid)
fun Iterable<Long?>.sum(): Long = fold(AddLongMonoid)
fun Iterable<Float?>.sum(): Float = fold(AddFloatMonoid)
fun Iterable<Double?>.sum(): Double = fold(AddDoubleMonoid)
fun Iterable<String?>.sum(): String = fold(AddStringMonoid)

fun <A> Iterable<A>.liftList(): ListContext<A> = ListContext(this.toList())

fun <A, B> Iterable<Option<A>>.mapOption(f: (A) -> B): List<Option<B>> = this.map { it.map(f) }
inline fun <A, B> Iterable<A?>.mapNullable(f: (A) -> B): List<B?> = this.map { it?.let(f) }
