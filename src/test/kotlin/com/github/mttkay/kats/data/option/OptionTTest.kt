package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.id.Id
import com.github.mttkay.kats.data.id.IdMonad
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionTTest {
  val optionT = OptionTBuilder<Id.F, String>(IdMonad)

  val some = optionT.some(Id("meow"))
  val none = optionT.none()

  @Test
  fun `isEmpty on None`() {
    assertThat(none.isEmpty).isEqualTo(Id(true))
  }

  @Test
  fun `isEmpty on Some`() {
    assertThat(some.isEmpty).isEqualTo(Id(false))
  }

  @Test
  fun `isDefined on None`() {
    assertThat(none.isDefined).isEqualTo(Id(false))
  }

  @Test
  fun `isDefined on Some`() {
    assertThat(some.isDefined).isEqualTo(Id(true))
  }

  @Test
  fun `getOrElse on Some`() {
    assertThat(some.getOrElse { "woof" }).isEqualTo(Id("meow"))
  }

  @Test
  fun `getOrElse on None`() {
    assertThat(none.getOrElse { "woof" }).isEqualTo(Id("woof"))
  }

  @Test
  fun `orElse on Some`() {
    assertThat(some.orElse { optionT.some(Id("woof")) }).isEqualTo(some)
  }

  @Test
  fun `orElse on None`() {
    val v = optionT.some(Id("woof"))
    assertThat(none.orElse { v }).isEqualTo(v)
  }

  @Test
  fun `map on Some`() {
    assertThat(some.map { it + "!" }).isEqualTo(optionT.some(Id("meow!")))
  }

  @Test
  fun `map on None`() {
    assertThat(none.map { it + "x" }).isEqualTo(none)
  }

  @Test
  fun `flatMap on Some`() {
    assertThat(some.flatMap { optionT.some(Id(it + "!")) }).isEqualTo(optionT.some(Id("meow!")))
  }

  @Test
  fun `flatMap on None`() {
    assertThat(none.flatMap { optionT.some(Id(it + "x")) }).isEqualTo(none)
  }

  @Test
  fun `subflatMap on None`() {
    assertThat(none.subflatMap { Option.Some(it + it) }).isEqualTo(none)
  }

  @Test
  fun `subflatMap on Some`() {
    assertThat(some.subflatMap { Option.Some("$it $it") })
        .isEqualTo(OptionT(Id(Option.Some("meow meow")), IdMonad))
  }

  @Test
  fun `toRight on Some`() {
    assertThat(some.toRight { -> "left" }.isRight).isEqualTo(Id(true))
  }

  @Test
  fun `toRight on None`() {
    assertThat(none.toRight { -> "left" }.isRight).isEqualTo(Id(false))
  }

  @Test
  fun `toLeft on Some`() {
    assertThat(some.toLeft { -> "right" }.isLeft).isEqualTo(Id(true))
  }

  @Test
  fun `toLeft on None`() {
    assertThat(none.toLeft { -> "right" }.isLeft).isEqualTo(Id(false))
  }
}