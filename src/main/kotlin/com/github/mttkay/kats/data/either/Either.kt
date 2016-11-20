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

  fun <S> fmap(f: (R) -> S): Either<L, S> = EitherFunctor.instance<L>().fmap(this, f)

}
