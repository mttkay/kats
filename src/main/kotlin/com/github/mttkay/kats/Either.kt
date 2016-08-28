package com.github.mttkay.kats

interface EitherFunctor<A, B> : Functor<B> {

  override fun <C> map(f: (B) -> C): Either<A, C>
}

sealed class Either<A, B>(open val value: Any?) : EitherFunctor<A, B> {

  class Left<A, B>(override val value: A) : Either<A, B>(value) {

    override val isLeft = true

    override val isRight = false

    override fun <C> map(f: (B) -> C): Left<A, C> = Left(value)
  }

  class Right<A, B>(override val value: B) : Either<A, B>(value) {

    override val isLeft = false

    override val isRight = true

    override fun <C> map(f: (B) -> C): Right<A, C> = Right(f(value))
  }

  abstract val isLeft: Boolean

  abstract val isRight: Boolean
}
