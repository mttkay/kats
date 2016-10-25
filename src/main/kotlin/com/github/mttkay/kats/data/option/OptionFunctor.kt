package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Functor

object OptionFunctor : Functor<Option.F> {

  override fun <A, B> fmap(fa: OptionKind<A>, f: (A) -> B): Option<B> {
    val option = Option.narrow(fa)
    return when (option) {
      is Option.Some -> Option.Some(f(option.value))
      is Option.None -> Option.None
    }
  }
}
