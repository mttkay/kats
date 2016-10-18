package com.github.mttkay.kats.ext.nullable

import com.github.mttkay.kats.data.option.Option
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NullableExtTest {

  @Test
  fun `liftOption for null`() {
    assertThat(null.liftOption()).isEqualTo(Option.None)
  }

  @Test
  fun `liftOption for value`() {
    assertThat(42.liftOption()).isEqualTo(Option.Some(42))
  }

  @Test
  fun `fold null`() {
    assertThat((null as Int?).fold("none") { it.toString() }).isEqualTo("none")
  }

  @Test
  fun `fold value`() {
    assertThat(42.fold("none") { it.toString() }).isEqualTo("42")
  }
}
