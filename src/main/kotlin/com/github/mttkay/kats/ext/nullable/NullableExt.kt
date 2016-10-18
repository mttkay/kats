package com.github.mttkay.kats.ext.nullable


inline fun <A, B> A?.fold(ifEmpty: B, f: (A) -> B): B =
    when {
      this == null -> ifEmpty
      else -> f(this)
    }
