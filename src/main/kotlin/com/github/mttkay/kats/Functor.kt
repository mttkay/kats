package com.github.mttkay.kats

interface Functor<F> {

  typealias _F<T> = K1<F, T>

  fun <A, B> fmap(fa: _F<A>, f: (A) -> B): _F<B>

}
