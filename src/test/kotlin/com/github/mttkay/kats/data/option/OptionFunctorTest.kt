package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.FunctorTest
import com.github.mttkay.kats.ext.nullable.toOption
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionFunctorTest : FunctorTest<Option.F, Option<A>>() {

  override val functor = OptionFunctor

  override val fa = Option.Some(a)

  @Test
  fun `map None`() {
    assertThat(null.toOption().map(Int::toString)).isEqualTo(Option.None)
  }

  @Test
  fun `map Some`() {
    assertThat(42.toOption().map(Int::toString)).isEqualTo(Option.Some("42"))
  }

}
