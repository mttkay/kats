package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.SemigroupK

object OptionSemigroupK : SemigroupK<Option.F> {
  override fun <A> combineK(fa1: OptionKind<A>, fa2: OptionKind<A>): Option<A> {
    val left = Option.narrow(fa1)
    val right = Option.narrow(fa2)
    return left.orElse { right }
  }
}
