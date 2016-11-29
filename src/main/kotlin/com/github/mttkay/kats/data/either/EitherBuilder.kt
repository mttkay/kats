package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Monoid

class EitherBuilder<L : Any, R : Any> {

  fun left(value: L): Either.Left<L, R> = Either.Left(value)

  fun right(value: R): Either.Right<L, R> = Either.Right(value)

  fun monoid(m: Monoid<R>): Monoid<Either<L, R>> = EitherMonoid(m)
}
