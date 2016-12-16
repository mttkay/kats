package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.ext.nullable.toOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionTest {

  @Test
  fun `lift nullable to Option when not null`() {
    assertThat(42.toOption()).isEqualTo(Some(42))
  }

  @Test
  fun `lift nullable to Option when null`() {
    assertThat((null as Int?).toOption()).isSameAs(None)
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

  @Test
  fun `fold None`() {
    assertThat(None.fold("0") { it.toString() }).isEqualTo("0")
  }

  @Test
  fun `fold Some`() {
    assertThat(Some(42).fold("0", Int::toString)).isEqualTo("42")
  }

  @Test
  fun `foldLeft None`() {
    assertThat(None.foldLeft("0") { b, a -> b + a }).isEqualTo("0")
  }

  @Test
  fun `foldLeft Some`() {
    assertThat(Some(42).foldLeft("0") { b, a -> b + a}).isEqualTo("042")
  }

  @Test
  fun `foldRight None`() {
    assertThat(None.foldRight("0") { a, b -> b + a }).isEqualTo("0")
  }

  @Test
  fun `foldRight Some`() {
    assertThat(Some(42).foldRight("0") { a, b -> b + a}).isEqualTo("042")
  }
}
