package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some

object OptionApplicative : Applicative<Option.F> {

  override fun <A> pure(a: A): Option<A> = Some(a)

  override fun <A, B> ap(fa: OptionKind<A>, ffa: OptionKind<(A) -> B>): Option<B> {
    val optionA = fa.narrow()
    val optionFAB = ffa.narrow()
    return when (optionA) {
      is Some -> {
        when (optionFAB) {
          is Some -> Some(optionFAB.value(optionA.value))
          else -> None
        }
      }
      else -> None
    }
  }
}
