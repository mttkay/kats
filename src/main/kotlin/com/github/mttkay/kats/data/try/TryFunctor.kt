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

infix fun <A, B> Try<A>.map(f: (A) -> B): Try<B> = TryFunctor.map(this, f)

fun <A, B> Try<A>.fproduct(f: (A) -> B): Try<Pair<A, B>> =
    TryFunctor.fproduct(this, f).narrowTry()

fun <A> Try<A>.void(): Try<Unit> =
    TryFunctor.void(this).narrowTry()
