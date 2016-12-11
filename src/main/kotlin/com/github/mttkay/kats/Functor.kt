package com.github.mttkay.kats

interface Functor<F> {

  typealias _F<T> = K1<F, T>

  fun <A, B> map(fa: _F<A>, f: (A) -> B): _F<B>

  fun <A, B> lift(f: (A) -> B): (_F<A>) -> _F<B> = { fa: _F<A> ->
    map(fa, f)
  }

  fun <A> void(fa: _F<A>): _F<Unit> = map(fa) { Unit }

  fun <A, B> fproduct(fa: _F<A>, f: (A) -> B): _F<Pair<A, B>> = map(fa) { Pair(it, f(it)) }
}
