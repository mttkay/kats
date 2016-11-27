package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.functions.compose
import com.github.mttkay.kats.mustEqual
import org.junit.Test

typealias A = Int
typealias B = String
typealias C = Boolean

val a: A = 42

fun f(a: A): B = a.toString()
fun g(b: B): C = b.toBoolean()

interface ApplicativeLaws<F, out K : K1<F, A>> {

  fun id(a: A): A = a

  val app: Applicative<F>

  val fa: K

  val fab: K1<F, (A) -> B>

  val fbc: K1<F, (B) -> C>

  @Test
  fun `ap identity`() =
      app.ap(fa, app.pure(this::id)) mustEqual fa

  @Test
  fun `ap homomorphism`() =
      app.ap(app.pure(a), app.pure(::f)) mustEqual app.pure(f(a))

  @Test
  fun `ap interchange`() =
      app.ap(app.pure(a), fab) mustEqual app.ap(fab, app.pure({ f: (A) -> B -> f(a) }))

  /**
   * Given f = (B -> C) -> (A -> B) -> (A -> C)
   *      fa: F<A>
   *     fab: F<A -> B>
   *     fbc: F<B -> C>
   *
   * Then
   *
   * fbc.ap(fab.ap(fa)) <=> fbc.fmap(f).ap(fab).ap(fa)
   */
  @Test
  fun `ap composition`() {
    val compose: ((B) -> C) -> ((A) -> B) -> (A) -> C = { bc -> { ab -> bc compose ab } }

    app.ap(app.ap(fa, fab), fbc) mustEqual app.ap(fa, app.ap(fab, app.fmap(fbc, compose)))
  }

  @Test
  fun `ap fmap`() =
      app.ap(fa, app.pure(::f)) mustEqual app.fmap(fa, ::f)

}
