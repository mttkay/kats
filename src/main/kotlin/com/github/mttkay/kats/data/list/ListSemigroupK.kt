package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.SemigroupK

object ListSemigroupK : SemigroupK<ListContext.F> {
  override fun <A> combineK(fa1: ListKind<A>, fa2: ListKind<A>): ListContext<A> {
    val left = ListContext.narrow(fa1).list
    val right = ListContext.narrow(fa2).list
    return ListContext(left + right)
  }
}
