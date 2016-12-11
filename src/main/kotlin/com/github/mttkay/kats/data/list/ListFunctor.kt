package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.ext.collection.liftList

object ListFunctor : Functor<ListContext.F> {
  override fun <A, B> map(fa: ListKind<A>, f: (A) -> B): ListContext<B> {
    return ListContext(fa.narrowList().list.map(f))
  }

  fun <A, B> liftList(f: (A) -> B): (List<A>) -> List<B> = { list: List<A> ->
    ListContext.lift(f).invoke(list.liftList()).list
  }
}

fun <A, B> ListContext.Companion.lift(f: (A) -> B): (ListContext<A>) -> ListContext<B> =
    ListFunctor.lift(f).narrowListFn()

fun <A, B> ListContext<A>.fproduct(f: (A) -> B): ListContext<Pair<A, B>> =
    ListFunctor.fproduct(this, f).narrowList()
