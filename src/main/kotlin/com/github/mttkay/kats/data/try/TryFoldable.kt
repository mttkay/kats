package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.Foldable
import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success

object TryFoldable : Foldable<Try.F> {

  override fun <A, B> foldLeft(fa: TryKind<A>, b: B, f: (B, A) -> B): B {
    val t = fa.narrowTry()
    return when (t) {
      is Success -> f(b, t.value)
      is Failure -> b
    }
  }

  override fun <A, B> foldRight(fa: TryKind<A>, b: B, f: (A, B) -> B): B {
    val t = fa.narrowTry()
    return when (t) {
      is Success -> f(t.value, b)
      is Failure -> b
    }
  }
}

fun <A> Try<A>.fold(m: Monoid<A>): A = TryFoldable.fold(this, m)

fun <A, B> Try<A>.foldLeft(b: B, f: (B, A) -> B): B = TryFoldable.foldLeft(this, b, f)

fun <A, B> Try<A>.foldRight(b: B, f: (A, B) -> B): B = TryFoldable.foldRight(this, b, f)

fun <A, B> Try<A>.foldMap(m: Monoid<B>, f: (A) -> B): B = TryFoldable.foldMap(this, m, f)
