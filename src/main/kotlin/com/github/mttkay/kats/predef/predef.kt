package com.github.mttkay.kats.predef

import com.github.mttkay.kats.Monoid

val IntMonoid: Monoid<Int> by lazy { Monoid.of(0) { a, b -> a + b } }
val FloatMonoid: Monoid<Float> by lazy { Monoid.of(0F) { a, b -> a + b } }
val DoubleMonoid: Monoid<Double> by lazy { Monoid.of(0.0) { a, b -> a + b } }
val StringMonoid: Monoid<String> by lazy { Monoid.of("") { a, b -> a + b } }
