package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionMonadTest {

  val m = OptionMonad

  @Test
  fun `flatMap Some`() {
    assertThat(m.flatMap(Some(42)) { Some(it * 2) }).isEqualTo(Some(84))
  }

  @Test
  fun `flatMap None`() {
    val none: Option<Int> = None
    assertThat(m.flatMap(none) { Some(it * 2) }).isEqualTo(None)
  }

}
