package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.Monad

object TryMonad : Monad<Try.F>, Applicative<Try.F> by TryApplicative {

  override fun <A, B> flatMap(fa: TryKind<A>, f: (A) -> TryKind<B>): Try<B> {
    val t = fa.narrowTry()
    return when (t) {
      is Try.Success -> f(t.value).narrowTry()
      is Try.Failure -> t.cast()
    }
  }
}

infix fun <A, B> Try<A>.flatMap(f: (A) -> Try<B>): Try<B> = TryMonad.flatMap(this, f)
