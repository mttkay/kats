package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.ext.collection.toListK

object ListMonad : Monad<ListK.F>, Applicative<ListK.F> by ListApplicative {

  override fun <A, B> flatMap(fa: ListKind<A>, f: (A) -> ListKind<B>): ListK<B> =
      fa.narrowList().list.flatMap { f(it).narrowList().list }.toListK()

}

infix fun <A, B> ListK<A>.flatMap(f: (A) -> ListK<B>): ListK<B> =
    ListMonad.flatMap(this, f)
