package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.ext.collection.liftList

object ListFunctor : Functor<ListK.F> {
  override fun <A, B> map(fa: ListKind<A>, f: (A) -> B): ListK<B> {
    return ListK(fa.narrowList().list.map(f))
  }

  fun <A, B> liftList(f: (A) -> B): (List<A>) -> List<B> = { list: List<A> ->
    ListK.lift(f).invoke(list.liftList()).list
  }
}

infix fun <A, B> ListK<A>.map(f: (A) -> B): ListK<B> =
    ListFunctor.map(this, f)

infix fun <A, B> ListK<A>.flatMap(f: (A) -> ListK<B>): ListK<B> =
    ListMonad.flatMap(this, f)

fun <A, B> ListK.Companion.lift(f: (A) -> B): (ListK<A>) -> ListK<B> =
    ListFunctor.lift(f).narrowListFn()

fun <A, B> ListK<A>.fproduct(f: (A) -> B): ListK<Pair<A, B>> =
    ListFunctor.fproduct(this, f).narrowList()
