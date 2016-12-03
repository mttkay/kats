package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.Monad

object OptionMonad : Monad<Option.F>, Applicative<Option.F> by OptionApplicative {

  override fun <A, B> flatMap(fa: OptionKind<A>, f: (A) -> OptionKind<B>): Option<B> {
    val option = fa.narrowOption()
    return when (option) {
      is Option.None -> Option.None
      is Option.Some -> f(option.value).narrowOption()
    }
  }
}
