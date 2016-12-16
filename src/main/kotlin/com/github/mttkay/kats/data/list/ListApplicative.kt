package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.ext.collection.toListK

object ListApplicative : Applicative<ListK.F> {

  override fun <A> pure(a: A): ListK<A> =
      ListK(listOf(a))

  override fun <A, B> ap(fa: ListKind<A>, ffa: ListKind<(A) -> B>): ListK<B> =
      ap(fa.narrowList().list, ffa.narrowList().list).toListK()

  fun <A, B> ap(fa: List<A>, ffa: List<(A) -> B>): List<B> =
      ffa.flatMap { f -> fa.map(f) }

}

infix fun <A, B> ListK<A>.product(that: ListK<B>): ListK<Pair<A, B>> =
    ListMonad.product(this, that).narrowList()

infix operator fun <A, B> ListK<A>.times(that: ListK<B>): ListK<Pair<A, B>> = product(that)
