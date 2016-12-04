package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success

object TryFunctor : Functor<Try.F> {

  override fun <A, B> map(fa: TryKind<A>, f: (A) -> B): Try<B> {
    val t = fa.narrowTry()
    return when (t) {
      is Success -> Try.run(t.value, f)
      is Failure -> t.cast()
    }
  }

}
