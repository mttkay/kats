package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.SemigroupK

object ListSemigroupK : SemigroupK<ListContext.F> {
  override fun <A> combineK(fa1: ListKind<A>, fa2: ListKind<A>): ListContext<A> {
    val left = fa1.narrowList().list
    val right = fa2.narrowList().list
    return ListContext(left + right)
  }
}
