package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.laws.applicative.*
import com.github.mttkay.kats.mustEqual
import org.junit.Test

abstract class MonadLaws<F, out K : K1<F, A>>(val m: Monad<F>) : ApplicativeLaws<F, K> {

  override val app: Applicative<F> = m

  abstract val mf: (A) -> K1<F, B>

  abstract val mg: (B) -> K1<F, C>

  /**
   * (pure(a) >>= f) == f(a)
   */
  @Test
  fun `pure left identity`() =
      m.flatMap(m.pure(a), mf) mustEqual mf(a)

  /**
   * (ma >>= pure(a)) == ma
   */
  @Test
  fun `pure right identity`() =
      m.flatMap(fa, { a: A -> m.pure(a) }) mustEqual fa

  /**
   * ((ma >>= f) >>= g) == (ma >>= (f(a) >>= g))
   */
  @Test
  fun `flatMap associativity`() =
    m.flatMap(m.flatMap(fa, mf), mg) mustEqual m.flatMap(fa) { m.flatMap(mf(a), mg) }

  @Test
  fun `map - flatMap coherence`() =
    m.flatMap(fa) { m.pure(f(it)) } mustEqual m.fmap(fa, ::f)
}
