package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.ext.collection.liftList

object ListApplicative : Applicative<ListContext.F> {

  override fun <A> pure(a: A): ListContext<A> =
      ListContext(listOf(a))

  override fun <A, B> ap(fa: ListKind<A>, ffa: ListKind<(A) -> B>): ListContext<B> =
      ap(ListContext.narrow(fa).list, ListContext.narrow(ffa).list).liftList()

  fun <A, B> ap(fa: List<A>, ffa: List<(A) -> B>): List<B> =
      ffa.flatMap { f -> fa.map(f) }

}
