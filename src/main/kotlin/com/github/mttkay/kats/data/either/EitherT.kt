package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monad


private typealias _E<F, L, R> = K1<F, Either<L, R>>

data class EitherT<F, L, R>(val value: K1<F, Either<L, R>>,
                            private val monad: Monad<F>) {

  private typealias _F<A> = K1<F, A>

  companion object {

    fun <F, L, R> left(v: K1<F, L>, monad: Monad<F>): EitherT<F, L, R> =
        EitherT(monad.map(v) { Either.left(it) }, monad)

    fun <F, L, R> right(v: K1<F, R>, monad: Monad<F>): EitherT<F, L, R> =
        EitherT(monad.map(v) { Either.right(it) }, monad)

  }

  val isLeft: _F<Boolean> = monad.map(value) { it.isLeft }

  val isRight: _F<Boolean> = monad.map(value) { it.isRight }

  fun getOrElse(default: () -> R): _F<R> = monad.map(value) { it.getOrElse(default) }

  fun <S> map(f: (R) -> S): EitherT<F, L, S> = EitherT(monad.map(value) { it.map(f) }, monad)

  fun <S> flatMap(f: (R) -> EitherT<F, L, S>): EitherT<F, L, S> =
      EitherT(monad.flatMap(value) {
        when (it) {
          is Either.Left -> monad.pure(it.rightCast())
          is Either.Right -> f(it.value).value
        }
      }, monad)

  fun <S> flatMapF(f: (R) -> _F<Either<L, S>>): EitherT<F, L, S> =
      flatMap { EitherT(f(it), monad) }

  fun <M, S> transform(f: (Either<L, R>) -> Either<M, S>): EitherT<F, M, S> =
      EitherT(monad.map(value) { f(it) }, monad)

  fun <S> subflatMap(f: (R) -> Either<L, S>): EitherT<F, L, S> =
      transform { it.flatMap(f) }
}
