package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.Functor
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.functions.compose
import com.github.mttkay.kats.mustEqual
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.f
import com.github.mttkay.kats.test.g
import org.junit.Test

interface FunctorLaws<F, out K : K1<F, A>> {

  fun id(a: A): A = a

  val functor: Functor<F>

  val fa: K

  /**
   * Preserves identity:
   *
   * map id = id
   */
  @Test
  fun `covariant identity`() =
      functor.map(fa, this::id) mustEqual fa

  /**
   * Preserves composition:
   *
   * (map g) o (map f) = map (g o f)
   */
  @Test
  fun `covariant composition`() =
      functor.map(functor.map(fa, ::f), ::g) mustEqual functor.map(fa, ::g compose ::f)

}
