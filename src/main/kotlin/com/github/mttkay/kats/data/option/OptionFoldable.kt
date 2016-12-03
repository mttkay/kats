package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Foldable
import com.github.mttkay.kats.K1

object OptionFoldable : Foldable<Option.F> {

  override fun <A, B> foldLeft(fa: OptionKind<A>, b: B, f: (B, A) -> B): B {
    val option = fa.narrowOption()
    return when (option) {
      is Option.None -> b
      is Option.Some -> f(b, option.value)
    }
  }

  override fun <A, B> foldRight(fa: K1<Option.F, A>, b: B, f: (A, B) -> B): B {
    val option = fa.narrowOption()
    return when (option) {
      is Option.None -> b
      is Option.Some -> f(option.value, b)
    }
  }
}
