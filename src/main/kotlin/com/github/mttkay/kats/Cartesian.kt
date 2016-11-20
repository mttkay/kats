package com.github.mttkay.kats

/**
 * [Cartesian] captures the idea of composing independent effectful values.
 * It is of particular interest when taken together with [Functor] - where [Functor]
 * captures the idea of applying a unary pure function to an effectful value,
 * calling `product` with `map` allows one to apply a function of arbitrary arity to multiple
 * independent effectful values.
 *
 * That same idea is also manifested in the form of [Apply], and indeed [Apply] extends both
 * [Cartesian] and [Functor] to illustrate this.
 */
interface Cartesian<F> {

  fun <A, B> product(fa: K1<F, A>, fb: K1<F, B>): K1<F, Pair<A, B>>

}
