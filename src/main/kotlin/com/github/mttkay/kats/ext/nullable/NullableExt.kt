package com.github.mttkay.kats.ext.nullable

import com.github.mttkay.kats.data.option.Option

fun <A> A?.toOption(): Option<A> =
    this?.let { Option.Some(it) } ?: Option.None
