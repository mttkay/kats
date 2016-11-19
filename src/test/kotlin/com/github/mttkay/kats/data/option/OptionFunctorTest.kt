package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.ext.nullable.liftOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionFunctorTest {

  @Test
  fun `fmap maps values to Some`() {
    assertThat(42.liftOption().fmap(Int::toString)).isEqualTo(Option.Some("42"))
  }

  @Test
  fun `map unlifts Some(value) to value`() {
    assertThat(42.liftOption().map(Int::toString)).isEqualTo("42")
  }

  @Test
  fun `map unlifts None to null`() {
    assertThat((null as Int?).liftOption().map(Int::toString)).isNull()
  }

}
