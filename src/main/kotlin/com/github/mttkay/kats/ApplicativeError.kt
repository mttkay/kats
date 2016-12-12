package com.github.mttkay.kats

import com.github.mttkay.kats.data.either.Either

interface ApplicativeError<F, E> : Applicative<F> {

  typealias _F<A> = K1<F, A>

  fun <A> raiseError(e: E): _F<A>

  fun <A> handleErrorWith(fa: _F<A>, f: (E) -> _F<A>): _F<A>

  fun <A> handleError(fa: _F<A>, f: (E) -> A): _F<A> =
      handleErrorWith(fa) { pure(f(it)) }

  fun <A> attempt(fa: _F<A>): _F<Either<E, A>> =
      handleErrorWith(map(fa) { Either.right(it) }) { e: E ->
        pure(Either.left(e))
      }
}
