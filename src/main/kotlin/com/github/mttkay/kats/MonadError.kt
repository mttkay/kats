package com.github.mttkay.kats

interface MonadError<F, E> : Monad<F>, ApplicativeError<F, E> {

  fun <A> ensure(fa: K1<F, A>, p: (A) -> Boolean, e: () -> E): K1<F, A> =
      flatMap(fa) {
        if (p(it)) pure(it) else raiseError(e())
      }

}
