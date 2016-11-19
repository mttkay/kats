package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Functor

interface EitherFunctor<out L> : Functor<EitherF<L>> {

  companion object {
    private val functorInstance = object : EitherFunctor<Any> {}

    @Suppress("UNCHECKED_CAST") // this is safe, since we never look at L
    fun <L> instance(): EitherFunctor<L> = functorInstance as EitherFunctor<L>
  }

  override fun <R, S> fmap(fa: EitherKind<L, R>, f: (R) -> S): Either<L, S> {
    val either = Either.narrow(fa)
    return when (either) {
      is Either.Left -> Either.Left(either.value)
      is Either.Right -> Either.Right(f(either.value))
    }
  }
}
