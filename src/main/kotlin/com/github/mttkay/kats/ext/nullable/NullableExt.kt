package com.github.mttkay.kats.ext.nullable

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.Semigroup

fun <T> nullableMonoid(sg: Semigroup<T>): Monoid<T?> = Monoid.of(null as T?) { a, b ->
  sg.maybeCombine(a, b)
}

inline fun <A, B> A?.fold(ifEmpty: B, f: (A) -> B): B =
    when {
      this == null -> ifEmpty
      else -> f(this)
    }
