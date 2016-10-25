package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.data.list.ListContext.Companion.narrow

object ListFunctor : Functor<ListContext.F> {
  override fun <A, B> fmap(fa: ListKind<A>, f: (A) -> B): ListContext<B> {
    return ListContext(narrow(fa).list.map(f))
  }
}
