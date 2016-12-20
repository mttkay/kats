package com.github.mttkay.kats.data.id

import com.github.mttkay.kats.Applicative

object IdApplicative : Applicative<Id.F> {

  override fun <A> pure(a: A): Id<A> = Id(a)

  override fun <A, B> ap(fa: IdKind<A>, ffa: IdKind<(A) -> B>): Id<B> {
    val a = fa.narrowId().value
    val fab = ffa.narrowId().value
    return Id(fab(a))
  }
}
