package com.github.mttkay.kats

interface MonadError<F, E> : Monad<F>, ApplicativeError<F, E> {

  typealias _F<A> = K1<F, A>

  fun <A> ensure(fa: _F<A>, p: (A) -> Boolean, e: () -> E): _F<A> =
      flatMap(fa) {
        if (p(it)) pure(it) else raiseError(e())
      }

}
