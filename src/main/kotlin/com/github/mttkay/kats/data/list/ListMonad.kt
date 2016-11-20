package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.ext.collection.liftList

object ListMonad : Monad<ListContext.F> {

  override fun <A> pure(a: A): ListContext<A> = ListApplicative.pure(a)

  override fun <A, B> ap(fa: ListKind<A>, ffa: ListKind<(A) -> B>): ListContext<B> =
      ListApplicative.ap(fa, ffa)

  override fun <A, B> flatMap(fa: ListKind<A>, f: (A) -> ListKind<B>): ListContext<B> =
      fa.narrow().list.flatMap { f(it).narrow().list }.liftList()

}
