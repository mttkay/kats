package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Functor

object ListFunctor : Functor<ListContext.F> {
  override fun <A, B> fmap(fa: ListKind<A>, f: (A) -> B): ListContext<B> {
    return ListContext(fa.narrowList().list.map(f))
  }
}
