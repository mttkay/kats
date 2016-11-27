package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.ext.collection.liftList

object ListMonad : Monad<ListContext.F>, Applicative<ListContext.F> by ListApplicative {

  override fun <A, B> flatMap(fa: ListKind<A>, f: (A) -> ListKind<B>): ListContext<B> =
      fa.narrow().list.flatMap { f(it).narrow().list }.liftList()

}
