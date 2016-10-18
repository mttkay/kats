package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.K2

sealed class Either<L, R> : K2<Either.F, L, R> {

  class F

  companion object {
    fun <L, R> narrow(either: K1<K1<Either.F, L>, R>) = either as Either<L, R>
  }

  class Left<L, R>(val value: L) : Either<L, R>() {

    override val isLeft = true

    override val isRight = false
  }

  class Right<L, R>(val value: R) : Either<L, R>() {

    override val isLeft = false

    override val isRight = true
  }

  abstract val isLeft: Boolean

  abstract val isRight: Boolean

  fun <S> fmap(f: (R) -> S) = object : EitherFunctor<L> {}.fmap(this, f)
}
