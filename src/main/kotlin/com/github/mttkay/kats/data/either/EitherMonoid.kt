package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right

class EitherMonoid<L, R>(private val monoidR: Monoid<R>) : Monoid<Either<L, R>> {

  override val empty: Right<L, R> = Right(monoidR.empty)

  override fun combine(a1: Either<L, R>, a2: Either<L, R>): Either<L, R> = when (a1) {
    is Left -> a1
    is Right -> when (a2) {
      is Left -> a2
      is Right -> Right(monoidR.combine(a1.value, a2.value))
    }
  }
}
