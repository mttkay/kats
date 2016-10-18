package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.K1

object ListFunctor : Functor<ListContext.F> {
  override fun <A, B> fmap(fa: K1<ListContext.F, A>, f: (A) -> B): ListContext<B> {
    val list: List<A> = ListContext.narrow(fa).list
    return ListContext(list.map(f))
  }
}
