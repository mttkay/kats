package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.MonadError

abstract class EitherMonadError<L> :
    Monad<EitherF<L>> by EitherMonad.instance<L>(),
    MonadError<EitherF<L>, L> {

  companion object {

    private val monadErrorInstance = object : EitherMonadError<Any>() {}

    @Suppress("UNCHECKED_CAST")
    fun <L> instance() = monadErrorInstance as EitherMonadError<L>
  }

  override fun <A> raiseError(e: L): Either<L, A> = Either.Left(e)

  override fun <A> handleErrorWith(fa: EitherKind<L, A>, f: (L) -> EitherKind<L, A>): Either<L, A> {
    val either = fa.narrowEither()
    return when (either) {
      is Either.Left -> f(either.value).narrowEither()
      is Either.Right -> either
    }
  }
}

fun <L, R> Either<L, R>.handleErrorWith(f: (L) -> Either<L, R>): Either<L, R> =
    EitherMonadError.instance<L>().handleErrorWith(this, f)

fun <L, R> Either<L, R>.handleError(f: (L) -> R): Either<L, R> =
    EitherMonadError.instance<L>().handleError(this, f).narrowEither()

fun <L, R> Either<L, R>.ensure(p: (R) -> Boolean, e: () -> L): Either<L, R> =
    EitherMonadError.instance<L>().ensure(this, p, e).narrowEither()

fun <L, R> Either<L, R>.attempt(): Either<L, Either<L, R>> = Either.right(this)
