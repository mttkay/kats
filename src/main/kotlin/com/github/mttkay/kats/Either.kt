package com.github.mttkay.kats

interface EitherFunctor<A, out B> : Functor<B> {

  override fun <C> map(f: (B) -> C): Either<A, C>
}

sealed class Either<A, out B> : EitherFunctor<A, B> {

  class Left<A, out B>(val value: A) : Either<A, B>() {

    override val isLeft = true

    override val isRight = false

    override fun <C> map(f: (B) -> C): Left<A, C> = Left(value)
  }

  class Right<A, out B>(val value: B) : Either<A, B>() {

    override val isLeft = false

    override val isRight = true

    override fun <C> map(f: (B) -> C): Right<A, C> = Right(f(value))
  }

  abstract val isLeft: Boolean

  abstract val isRight: Boolean
}
