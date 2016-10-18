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
  fun `fmap maps values to Some`() {
    assertThat(42.liftOption().fmap { it.toString() }).isEqualTo(Some("42"))
  }

  @Test
  fun `map unlifts Some(value) to value`() {
    assertThat(42.liftOption().map { it.toString() }).isEqualTo("42")
  }

  @Test
  fun `map unlifts None to null`() {
    assertThat((null as Int?).liftOption().map { it.toString() }).isNull()
  }

  @Test
  fun `getOrElse for Some value`() {
    assertThat(Some(42).getOrElse { 0 }).isEqualTo(42)
  }

  @Test
  fun `getOrElse for None`() {
    assertThat(None.getOrElse { 0 }).isEqualTo(0)
  }
}
