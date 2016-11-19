package com.github.mttkay.kats

interface SemigroupK<F> {

  fun <A> combineK(fa1: K1<F, A>, fa2: K1<F, A>): K1<F, A>

  fun <A> algebra(): Semigroup<K1<F, A>> = object : Semigroup<K1<F, A>> {
    override fun combine(a1: K1<F, A>, a2: K1<F, A>): K1<F, A> = combineK(a1, a2)
  }

}
