package com.github.mttkay.kats.data.id

import com.github.mttkay.kats.Functor

object IdFunctor : Functor<Id.F> {
  override fun <A, B> map(fa: IdKind<A>, f: (A) -> B): Id<B> {
    val a = fa.narrowId().value
    return Id(f(a))
  }
}

fun <A, B> Id<A>.map(f: (A) -> B): Id<B> = IdFunctor.map(this, f)
