package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1

typealias EitherF<L> = K1<Either.F, L>
typealias EitherKind<L, R> = K1<EitherF<L>, R>

@Suppress("UNCHECKED_CAST")
fun <L, R> EitherKind<L, R>.narrowEither() = this as Either<L, R>

@Suppress("UNCHECKED_CAST")
fun <L, R, S> ((EitherKind<L, R>) -> EitherKind<L, S>).narrowEitherFn() = this as ((Either<L, R>) -> Either<L, S>)

@Suppress("UNCHECKED_CAST")
fun <L, R, F> K1<F, EitherKind<L, R>>.narrowInnerEither() = this as K1<F, Either<L, R>>

@Suppress("UNCHECKED_CAST") // safe, because operates on Right
fun <L, R> Either.Right<*, R>.leftCast(): Either<L, R> = this as Either.Right<L, R>

@Suppress("UNCHECKED_CAST") // safe, because operates on Left
fun <L, R> Either.Left<L, *>.rightCast(): Either<L, R> = this as Either.Left<L, R>

sealed class Either<out L, out R> : EitherKind<L, R> {

  class F

  companion object {

    fun <L> left(v: L): Either<L, Nothing> = Left(v)

    fun <R> right(v: R): Either<Nothing, R> = Right(v)

    fun <L : Any, R : Any> builder() = EitherBuilder<L, R>()
  }

  class Left<out L, out R>(val value: L) : Either<L, R>() {

    override val isLeft = true

    override val isRight = false

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other?.javaClass != javaClass) return false

      other as Left<*, *>

      if (value != other.value) return false

      return true
    }

    override fun hashCode(): Int {
      return value?.hashCode() ?: 0
    }

    override fun toString(): String = "Left($value)"
  }

  class Right<out L, out R>(val value: R) : Either<L, R>() {

    override val isLeft = false

    override val isRight = true

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other?.javaClass != javaClass) return false

      other as Right<*, *>

      if (value != other.value) return false

      return true
    }

    override fun hashCode(): Int {
      return value?.hashCode() ?: 0
    }

    override fun toString(): String = "Right($value)"
  }

  abstract val isLeft: Boolean

  abstract val isRight: Boolean

  fun <S> fold(ifLeft: S, f: (R) -> S): S = when (this) {
    is Left -> ifLeft
    is Right -> f(value)
  }
}

inline fun <L, R> Either<L, R>.getOrElse(default: () -> R): R = when (this) {
  is Either.Left -> default()
  is Either.Right -> value
}

inline fun <L, R> Either<L, R>.orElse(default: () -> Either<L, R>): Either<L, R> = when (this) {
  is Either.Left -> default()
  is Either.Right -> this
}
