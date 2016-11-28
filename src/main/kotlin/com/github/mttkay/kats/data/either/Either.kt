package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1

typealias EitherF<L> = K1<Either.F, L>
typealias EitherKind<L, R> = K1<EitherF<L>, R>

@Suppress("UNCHECKED_CAST")
fun <L, R> EitherKind<L, R>.narrow() = this as Either<L, R>
@Suppress("UNCHECKED_CAST") // safe, because operates on Right
fun <L, R> Either.Right<*, R>.leftCast() = this as Either.Right<L, R>
@Suppress("UNCHECKED_CAST") // safe, because operates on Left
fun <L, R> Either.Left<L, *>.rightCast() = this as Either.Left<L, R>

sealed class Either<L, out R> : EitherKind<L, R> {

  class F

  class Left<L, out R>(val value: L) : Either<L, R>() {

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

  class Right<L, out R>(val value: R) : Either<L, R>() {

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

  fun <S> fmap(f: (R) -> S): Either<L, S> = EitherFunctor.instance<L>().fmap(this, f)

  fun <S> flatMap(f: (R) -> Either<L, S>): Either<L, S> = EitherMonad.instance<L>().flatMap(this, f)

  fun <S> foldLeft(s: S, f: (S, R) -> S): S = EitherFoldable.instance<L>().foldLeft(this, s, f)

  fun <S> foldRight(s: S, f: (R, S) -> S): S = EitherFoldable.instance<L>().foldRight(this, s, f)
}
