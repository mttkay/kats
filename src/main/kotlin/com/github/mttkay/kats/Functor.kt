package com.github.mttkay.kats

interface Functor<F> {

  fun <A, B> fmap(fa: K1<F, A>, f: (A) -> B): K1<F, B>

}
