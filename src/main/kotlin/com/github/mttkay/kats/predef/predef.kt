package com.github.mttkay.kats.predef

import com.github.mttkay.kats.Monoid

/*
Monoids
 */
val AddIntMonoid: Monoid<Int> by lazy { Monoid.create(0) { a, b -> a + b } }
val AddLongMonoid: Monoid<Long> by lazy { Monoid.create(0L) { a, b -> a + b } }
val AddFloatMonoid: Monoid<Float> by lazy { Monoid.create(0F) { a, b -> a + b } }
val AddDoubleMonoid: Monoid<Double> by lazy { Monoid.create(0.0) { a, b -> a + b } }
val AddStringMonoid: Monoid<String> by lazy { Monoid.create("") { a, b -> a + b } }

val MulIntMonoid: Monoid<Int> by lazy { Monoid.create(1) { a, b -> a * b } }
val MulLongMonoid: Monoid<Long> by lazy { Monoid.create(1L) { a, b -> a * b } }
val MulFloatMonoid: Monoid<Float> by lazy { Monoid.create(1F) { a, b -> a * b } }
val MulDoubleMonoid: Monoid<Double> by lazy { Monoid.create(1.0) { a, b -> a * b } }

val BoolOrMonoid: Monoid<Boolean> by lazy { Monoid.create(false) { a, b -> a or b } }
val BoolAndMonoid: Monoid<Boolean> by lazy { Monoid.create(true) { a, b -> a and b } }
