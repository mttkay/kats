package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.MonadError
import com.github.mttkay.kats.data.either.Either

object TryMonadError :
    Monad<Try.F> by TryMonad,
    MonadError<Try.F, Throwable> {

  override fun <A> raiseError(e: Throwable): Try<A> = Try.Failure(e)

  override fun <A> handleErrorWith(fa: TryKind<A>, f: (Throwable) -> TryKind<A>): Try<A> {
    val t = fa.narrowTry()
    return when (t) {
      is Try.Success -> t
      is Try.Failure -> f(t.exception).narrowTry()
    }
  }

}

fun <A> Try<A>.handleErrorWith(f: (Throwable) -> Try<A>): Try<A> =
    TryMonadError.handleErrorWith(this, f)

fun <A> Try<A>.handleError(f: (Throwable) -> A): Try<A> =
    TryMonadError.handleError(this, f).narrowTry()

fun <A> Try<A>.ensure(p: () -> Boolean, e: () -> Throwable): Try<A> =
    TryMonadError.ensure(this, p, e).narrowTry()

fun <A> Try<A>.attempt(): Try<Either<Throwable, A>> =
    TryMonadError.attempt(this).narrowTry()
