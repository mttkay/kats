package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right

interface EitherApplicative<L> : Applicative<EitherF<L>> {

  companion object {
    private val appInstance = object : EitherApplicative<Any> {}

    @Suppress("UNCHECKED_CAST")
    fun <L> instance(): EitherApplicative<L> = appInstance as EitherApplicative<L>
  }

  override fun <A> pure(a: A): Either<L, A> = Right(a)

  override fun <A, B> ap(fa: EitherKind<L, A>, ffa: EitherKind<L, (A) -> B>): Either<L, B> {
    val eitherLA = fa.narrowEither()
    val eitherFAB = ffa.narrowEither()
    return when (eitherLA) {
      is Right -> {
        when (eitherFAB) {
          is Right -> Right(eitherFAB.value(eitherLA.value))
          is Left -> eitherFAB.rightCast()
        }
      }
      is Left -> eitherLA.rightCast()
    }
  }
}
