package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monad

class EitherTBuilder<F, L : Any, R : Any>(private val monad: Monad<F>) {

  fun of(value: K1<F, Either<L, R>>): EitherT<F, L, R> = EitherT(value, monad)

  fun left(value: K1<F, L>): EitherT<F, L, R> = EitherT.left(value, monad)

  fun right(value: K1<F, R>): EitherT<F, L, R> = EitherT.right(value, monad)
}
