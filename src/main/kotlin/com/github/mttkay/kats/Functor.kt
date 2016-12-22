package com.github.mttkay.kats

interface Functor<F> {

  fun <A, B> map(fa: K1<F, A>, f: (A) -> B): K1<F, B>

  fun <A, B> lift(f: (A) -> B): (K1<F, A>) -> K1<F, B> = { fa: K1<F, A> ->
    map(fa, f)
  }

  fun <A> void(fa: K1<F, A>): K1<F, Unit> = map(fa) { Unit }

  fun <A, B> fproduct(fa: K1<F, A>, f: (A) -> B): K1<F, Pair<A, B>> = map(fa) { Pair(it, f(it)) }
}
