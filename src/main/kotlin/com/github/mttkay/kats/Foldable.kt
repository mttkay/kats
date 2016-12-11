package com.github.mttkay.kats

interface Foldable<in F> {

  fun <A> fold(fa: K1<F, A>, m: Monoid<A>): A =
      foldLeft(fa, m.empty) { a1, a2 -> m.combine(a1, a2) }

  fun <A, B> foldLeft(fa: K1<F, A>, b: B, f: (B, A) -> B): B

  fun <A, B> foldRight(fa: K1<F, A>, b: B, f: (A, B) -> B): B

  fun <A, B> foldMap(fa: K1<F, A>, m: Monoid<B>, f: (A) -> B): B =
      foldLeft(fa, m.empty) { b, a -> m.combine(b, f(a)) }
}
