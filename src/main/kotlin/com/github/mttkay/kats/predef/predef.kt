package com.github.mttkay.kats.predef

import com.github.mttkay.kats.Monoid

val AddIntMonoid: Monoid<Int> by lazy { Monoid.of(0) { a, b -> a + b } }
val AddLongMonoid: Monoid<Long> by lazy { Monoid.of(0L) { a, b -> a + b } }
val AddFloatMonoid: Monoid<Float> by lazy { Monoid.of(0F) { a, b -> a + b } }
val AddDoubleMonoid: Monoid<Double> by lazy { Monoid.of(0.0) { a, b -> a + b } }
val AddStringMonoid: Monoid<String> by lazy { Monoid.of("") { a, b -> a + b } }

val MulIntMonoid: Monoid<Int> by lazy { Monoid.of(1) { a, b -> a * b } }
val MulLongMonoid: Monoid<Long> by lazy { Monoid.of(1L) { a, b -> a * b } }
val MulFloatMonoid: Monoid<Float> by lazy { Monoid.of(1F) { a, b -> a * b } }
val MulDoubleMonoid: Monoid<Double> by lazy { Monoid.of(1.0) { a, b -> a * b } }
