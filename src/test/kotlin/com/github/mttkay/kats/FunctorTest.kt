package com.github.mttkay.kats

import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.f
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


abstract class FunctorTest<F, out K : K1<F, A>> {

  abstract val functor: Functor<F>

  abstract val fa: K

  @Test
  fun `fproduct`() {
    assertThat(functor.fproduct(fa, ::f)).isEqualTo(functor.map(fa) { Pair(it, f(it)) })
  }

  @Test
  fun `void`() {
    assertThat(functor.void(fa)).isEqualTo(functor.map(fa) { Unit })
  }

}
