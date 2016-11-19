package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionSemigroupKTest {

  @Test
  fun `combineK when left is Some and right is None`() {
    val combined = OptionSemigroupK.combineK(Some(42), None)

    assertThat(combined).isEqualTo(Some(42))
  }

  @Test
  fun `combineK when left is None and right is Some`() {
    val combined = OptionSemigroupK.combineK(None, Some(42))

    assertThat(combined).isEqualTo(Some(42))
  }

  @Test
  fun `combineK when both are Some`() {
    val combined = OptionSemigroupK.combineK(Some(44), Some(42))

    assertThat(combined).isEqualTo(Some(44))
  }

  @Test
  fun `combineK when both are None`() {
    val combined = OptionSemigroupK.combineK(None, None)

    assertThat(combined).isEqualTo(None)
  }

}

