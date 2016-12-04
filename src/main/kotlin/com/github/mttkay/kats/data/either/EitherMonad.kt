package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Monad

interface EitherMonad<L> : EitherApplicative<L>, Monad<EitherF<L>> {

  companion object {

    private val monadInstance = object : EitherMonad<Any> {}

    @Suppress("UNCHECKED_CAST") // this is safe, as we never look at L
    fun <L> instance() = monadInstance as EitherMonad<L>
  }

  override fun <A, B> flatMap(fa: EitherKind<L, A>, f: (A) -> EitherKind<L, B>): Either<L, B> {
    val either = fa.narrowEither()
    return when (either) {
      is Either.Left -> either.rightCast()
      is Either.Right -> f(either.value).narrowEither()
    }
  }
}
