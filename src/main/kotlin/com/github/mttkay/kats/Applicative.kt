package com.github.mttkay.kats

/**
 * The basis for any applicative functor.
 *
 * Given a context `F`, and types `A` and `(A) -> B` in `F`,
 * [ap] describes how to obtain `B` in `F`. With the help of [pure], we can lift
 * "raw" values of some `A` into `F`. If `A` is a function type, we get [fmap],
 * i.e. a functor instance, for free.
 *
 * [Applicative] also affords us with the ability to generalize the creation of
 * products of arbitrary type constructors. That is, given context `F` for types
 * `A` and `B`, we can obtain the product `(A, B)` in the same context `F`.
 *
 * ## Differences to [Monad]
 * The structure of an Applicative computation is fixed, whereas the structure of a Monad
 * computation can change based on intermediate results. This also means that parsers
 * built using an Applicative interface can only parse context-free languages; in order
 * to parse context-sensitive languages a Monad interface is needed.
 *
 * See https://wiki.haskell.org/Typeclassopedia#Intuition_3
 *
 * Must obey the laws defined in [ApplicativeLaws].
 */
interface Applicative<F> : Functor<F>, Cartesian<F> {

  /**
   * Lifts an `A` into an `F` context.
   */
  fun <A> pure(a: A): K1<F, A>

  /**
   * Given a context `F<A>` and a context `F<(A) -> B>`, returns a new context `F<B>`.
   */
  fun <A, B> ap(fa: K1<F, A>, ffa: K1<F, (A) -> B>): K1<F, B>

  override fun <A, B> product(fa: K1<F, A>, fb: K1<F, B>): K1<F, Pair<A, B>> =
      ap(fb, fmap(fa) { a: A -> { b: B -> Pair(a, b) } })

  override fun <A, B> fmap(fa: K1<F, A>, f: (A) -> B): K1<F, B> = ap(fa, pure(f))

  fun <A, B, Z> map2(fa: K1<F, A>, fb: K1<F, B>, f: (Pair<A, B>) -> Z): K1<F, Z> =
      fmap(product(fa, fb), f)
}
