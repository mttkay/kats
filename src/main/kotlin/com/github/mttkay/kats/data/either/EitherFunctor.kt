package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Functor

interface EitherFunctor<L> : Functor<EitherF<L>> {

  companion object {
    private val functorInstance = object : EitherFunctor<Any> {}

    @Suppress("UNCHECKED_CAST") // this is safe, since we never look at L
    fun <L> instance(): EitherFunctor<L> = functorInstance as EitherFunctor<L>
  }

  override fun <R, S> map(fa: EitherKind<L, R>, f: (R) -> S): Either<L, S> {
    val either = fa.narrowEither()
    return when (either) {
      is Either.Left -> Either.Left(either.value)
      is Either.Right -> Either.Right(f(either.value))
    }
  }
}

infix fun <L, R, S> Either<L, R>.map(f: (R) -> S): Either<L, S> = EitherFunctor.instance<L>().map(this, f)

fun <L, R, S> Either.Companion.lift(f: (R) -> S): (Either<L, R>) -> Either<L, S> =
    EitherFunctor.instance<L>().lift(f).narrowEitherFn()

fun <L, R, S> Either<L, R>.fproduct(f: (R) -> S): Either<L, Pair<R, S>> =
    EitherFunctor.instance<L>().fproduct(this, f).narrowEither()

fun <L, R> Either<L, R>.void(): Either<L, Unit> =
    EitherFunctor.instance<L>().void(this).narrowEither()
