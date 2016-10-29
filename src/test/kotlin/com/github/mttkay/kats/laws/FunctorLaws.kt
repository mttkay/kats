package com.github.mttkay.kats.laws

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.functions.compose
import com.github.mttkay.kats.mustEqual
import org.junit.Test

typealias A = Int
typealias B = String
typealias C = Boolean

val a: A = 0

fun f(a: A): B = a.toString()
fun g(b: B): C = b.toBoolean()

interface FunctorLaws<F, out K : K1<F, A>> {

  fun id(a: A): A = a

  val functor: Functor<F>

  val fa: K

  /**
   * Preserves identity:
   *
   * fmap id = id
   */
  @Test
  fun `covariant identity`() =
      functor.fmap(fa, this::id) mustEqual fa

  /**
   * Preserves composition:
   *
   * (fmap g) o (fmap f) = fmap (g o f)
   */
  @Test
  fun `covariant composition`() =
      functor.fmap(functor.fmap(fa, ::f), ::g) mustEqual functor.fmap(fa, ::g compose ::f)

}
