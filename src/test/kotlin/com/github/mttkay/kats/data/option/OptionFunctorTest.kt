package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.ext.nullable.liftOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionFunctorTest {

  @Test
  fun `map None`() {
    assertThat(null.liftOption().map(Int::toString)).isEqualTo(Option.None)
  }

  @Test
  fun `map Some`() {
    assertThat(42.liftOption().map(Int::toString)).isEqualTo(Option.Some("42"))
  }

}
