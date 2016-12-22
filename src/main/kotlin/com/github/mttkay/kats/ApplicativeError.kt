package com.github.mttkay.kats

import com.github.mttkay.kats.data.either.Either

interface ApplicativeError<F, E> : Applicative<F> {

  fun <A> raiseError(e: E): K1<F, A>

  fun <A> handleErrorWith(fa: K1<F, A>, f: (E) -> K1<F, A>): K1<F, A>

  fun <A> handleError(fa: K1<F, A>, f: (E) -> A): K1<F, A> =
      handleErrorWith(fa) { pure(f(it)) }

  fun <A> attempt(fa: K1<F, A>): K1<F, Either<E, A>> =
      handleErrorWith(map(fa) { Either.right(it) }) { e: E ->
        pure(Either.left(e))
      }
}
