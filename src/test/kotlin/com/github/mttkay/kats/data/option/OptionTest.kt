package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.ext.nullable.liftOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionTest {

  @Test
  fun `lift nullable to Option when not null`() {
    assertThat(42.liftOption()).isEqualTo(Some(42))
  }

  @Test
  fun `lift nullable to Option when null`() {
    assertThat((null as Int?).liftOption()).isSameAs(None)
  }

  @Test
  fun `getOrElse for Some`() {
    assertThat(Some(42).getOrElse { 0 }).isEqualTo(42)
  }

  @Test
  fun `getOrElse for None`() {
    assertThat(None.getOrElse { 0 }).isEqualTo(0)
  }

  @Test
  fun `orElse for Some`() {
    assertThat(Some(42).orElse { Some(0) }).isEqualTo(Some(42))
  }

  @Test
  fun `orElse for None`() {
    assertThat(None.orElse { Some(0) }).isEqualTo(Some(0))
  }
}
