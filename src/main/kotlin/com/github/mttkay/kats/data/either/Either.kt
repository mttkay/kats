package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1

typealias EitherF<L> = K1<Either.F, L>
typealias EitherKind<L, R> = K1<EitherF<L>, R>

sealed class Either<out L, out R> : EitherKind<L, R> {

  class F

  companion object {
    fun <L, R> narrow(either: EitherKind<L, R>) = either as Either<L, R>
  }

  class Left<out L, out R>(val value: L) : Either<L, R>() {

    override val isLeft = true

    override val isRight = false
  }

  class Right<out L, out R>(val value: R) : Either<L, R>() {

    override val isLeft = false

    override val isRight = true
  }

  abstract val isLeft: Boolean

  abstract val isRight: Boolean

  fun <S> fmap(f: (R) -> S) = object : EitherFunctor<L> {}.fmap(this, f)
}
