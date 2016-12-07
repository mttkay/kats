package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some

object OptionApplicative : Applicative<Option.F> {

  override fun <A> pure(a: A): Option<A> = Some(a)

  override fun <A, B> ap(fa: OptionKind<A>, ffa: OptionKind<(A) -> B>): Option<B> {
    val optionA = fa.narrowOption()
    val optionFAB = ffa.narrowOption()
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

infix fun <A, B> Option<A>.product(that: Option<B>): Option<Pair<A, B>> =
    OptionApplicative.product(this, that).narrowOption()

infix operator fun <A, B> Option<A>.times(that: Option<B>): Option<Pair<A, B>> = product(that)
