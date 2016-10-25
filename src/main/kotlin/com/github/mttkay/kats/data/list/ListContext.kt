package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1

typealias ListKind<A> = K1<ListContext.F, A>

class ListContext<out A>(val list: List<A>) : ListKind<A> {
  class F

  companion object {
    fun <A> narrow(fa: ListKind<A>): ListContext<A> = fa as ListContext<A>
  }

  fun <B> fmap(f: (A) -> B): ListContext<B> = ListFunctor.fmap(this, f)
  fun <B> map(f: (A) -> B): List<B> = fmap(f).list

}
