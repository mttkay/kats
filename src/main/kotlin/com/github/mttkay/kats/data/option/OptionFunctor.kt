package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Functor

object OptionFunctor : Functor<Option.F> {

  override fun <A, B> map(fa: OptionKind<A>, f: (A) -> B): Option<B> {
    val option = fa.narrowOption()
    return when (option) {
      is Option.Some -> Option.Some(f(option.value))
      is Option.None -> Option.None
    }
  }
}

infix fun <A, B> Option<A>.map(f: (A) -> B): Option<B> = OptionFunctor.map(this, f)

fun <A, B> Option<A>.fproduct(f: (A) -> B): Option<Pair<A, B>> =
    OptionFunctor.fproduct(this, f).narrowOption()

fun <A> Option<A>.void(): Option<Unit> =
    OptionFunctor.void(this).narrowOption()
