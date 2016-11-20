package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.mustEqual
import org.junit.Test

typealias A = Int
typealias B = String
typealias C = Boolean

val a: A = 42

fun f(a: A): B = a.toString()

interface ApplicativeLaws<F, out K : K1<F, A>, out KF : K1<F, (A) -> B>> {

  fun id(a: A): A = a

  val app: Applicative<F>

  val fa: K

  val ffa: KF

  @Test
  fun `ap identity`() =
      app.ap(fa, app.pure(this::id)) mustEqual fa

  @Test
  fun `ap homomorphism`() =
      app.ap(app.pure(a), app.pure(::f)) mustEqual app.pure(f(a))

  @Test
  fun `ap interchange`() =
      app.ap(app.pure(a), ffa) mustEqual app.ap(ffa, app.pure({ f: (A) -> B -> f(a) }))

  @Test
  fun `ap fmap`() =
      app.ap(fa, app.pure(::f)) mustEqual app.fmap(fa, ::f)

}
