package com.github.mttkay.kats

interface Functor<A> {

  fun <B> map(f: (A) -> B): Functor<B>

}
