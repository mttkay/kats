package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.MonadError
import com.github.mttkay.kats.data.either.Either

object OptionMonadError :
    Monad<Option.F> by OptionMonad,
    MonadError<Option.F, Unit> {

  override fun <A> raiseError(e: Unit): Option<A> = Option.None

  override fun <A> handleErrorWith(fa: OptionKind<A>, f: (Unit) -> OptionKind<A>): Option<A> {
    val option = fa.narrowOption()
    return when (option) {
      is Option.Some -> option
      is Option.None -> f(Unit).narrowOption()
    }
  }
}

inline fun <A> Option<A>.handleErrorWith(crossinline f: () -> Option<A>): Option<A> =
    OptionMonadError.handleErrorWith(this) { f() }

inline fun <A> Option<A>.handleError(crossinline f: () -> A): Option<A> =
    OptionMonadError.handleError(this) { f() }.narrowOption()

fun <A> Option<A>.ensure(p: (A) -> Boolean, e: () -> Unit): Option<A> =
    OptionMonadError.ensure(this, p, e).narrowOption()

fun <A> Option<A>.attempt(): Option<Either<Unit, A>> =
    OptionMonadError.attempt(this).narrowOption()
