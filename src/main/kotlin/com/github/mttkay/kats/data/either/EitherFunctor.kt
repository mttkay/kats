package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.K1

interface EitherFunctor<L> : Functor<K1<Either.F, L>> {
  override fun <R, S> fmap(fa: K1<K1<Either.F, L>, R>, f: (R) -> S): Either<L, S> {
    val either = Either.narrow(fa)
    return when (either) {
      is Either.Left -> Either.Left(either.value)
      is Either.Right -> Either.Right(f(either.value))
    }
  }
}
