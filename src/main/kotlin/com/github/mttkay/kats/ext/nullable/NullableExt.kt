package com.github.mttkay.kats.ext.nullable

import com.github.mttkay.kats.data.option.Option

inline fun <A, B> A?.fold(ifEmpty: B, f: (A) -> B): B =
    when {
      this == null -> ifEmpty
      else -> f(this)
    }

fun <A> A?.liftOption(): Option<A> =
    this?.let { Option.Some(it) } ?: Option.None
