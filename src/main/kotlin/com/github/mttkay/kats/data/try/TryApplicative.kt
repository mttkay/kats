package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success

object TryApplicative : Applicative<Try.F> {

  override fun <A> pure(a: A): Try<A> = Success(a)

  override fun <A, B> ap(fa: TryKind<A>, ffa: TryKind<(A) -> B>): Try<B> {
    val t = fa.narrowTry()
    val tf = ffa.narrowTry()
    return when (t) {
      is Success -> when (tf) {
        is Success -> Success(tf.value(t.value))
        is Failure -> tf.cast<B>()
      }
      is Failure -> t.cast()
    }
  }
}

infix fun <A, B> Try<A>.product(that: Try<B>): Try<Pair<A, B>> =
    TryApplicative.product(this, that).narrowTry()

infix operator fun <A, B> Try<A>.times(that: Try<B>): Try<Pair<A, B>> = product(that)
