package com.github.mttkay.kats.ext.nullable

import com.github.mttkay.kats.data.option.Option
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NullableExtTest {

  @Test
  fun `toOption for null`() {
    assertThat(null.toOption()).isEqualTo(Option.None)
  }

  @Test
  fun `toOption for value`() {
    assertThat(42.toOption()).isEqualTo(Option.Some(42))
  }
}
