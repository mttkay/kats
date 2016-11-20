package com.github.mttkay.kats

interface Monad<F> : Applicative<F> {

  fun <A, B> flatMap(fa: K1<F, A>, f: (A) -> K1<F, B>): K1<F, B>

  fun <A> flatten(fa: K1<F, K1<F, A>>): K1<F, A> = flatMap(fa) { it }
}
