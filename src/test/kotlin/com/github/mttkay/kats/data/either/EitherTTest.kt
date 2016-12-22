package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.data.id.Id
import com.github.mttkay.kats.data.id.IdMonad
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTTest {

  val eitherT = EitherTBuilder<Id.F, String, Int>(IdMonad)

  val left = eitherT.left(Id("bad"))
  val right = eitherT.right(Id(42))

  @Test
  fun `isLeft on Left`() {
    assertThat(left.isLeft).isEqualTo(Id(true))
  }

  @Test
  fun `isLeft on Right`() {
    assertThat(right.isLeft).isEqualTo(Id(false))
  }

  @Test
  fun `isRight on Left`() {
    assertThat(left.isRight).isEqualTo(Id(false))
  }

  @Test
  fun `isRight on Right`() {
    assertThat(right.isRight).isEqualTo(Id(true))
  }

  @Test
  fun `getOrElse on Left`() {
    assertThat(left.getOrElse { 42 }).isEqualTo(Id(42))
  }

  @Test
  fun `getOrElse on Right`() {
    assertThat(right.getOrElse { 0 }).isEqualTo(Id(42))
  }

  @Test
  fun `map on Left`() {
    assertThat(left.map { it * 2 }).isEqualTo(left)
  }

  @Test
  fun `map on Right`() {
    assertThat(right.map { it * 2 }).isEqualTo(eitherT.right(Id(84)))
  }

  @Test
  fun `flatMap on Left`() {
    assertThat(left.flatMap { eitherT.right(Id(it * 2)) }).isEqualTo(left)
  }

  @Test
  fun `flatMap on Right`() {
    assertThat(right.flatMap { eitherT.right(Id(it * 2)) }).isEqualTo(eitherT.right(Id(84)))
  }

  @Test
  fun `flatMapF on Left`() {
    assertThat(left.flatMapF { Id(Either.right(it * 2)) }).isEqualTo(left)
  }

  @Test
  fun `flatMapF on Right`() {
    assertThat(right.flatMapF { Id(Either.right(it * 2)) }).isEqualTo(eitherT.right(Id(84)))
  }

  @Test
  fun `subflatMap on Left`() {
    assertThat(left.subflatMap { Either.right(it.toString()) }).isEqualTo(left)
  }

  @Test
  fun `subflatMap on Right`() {
    assertThat(right.subflatMap { Either.right(it.toString()) }).isEqualTo(EitherT(Id(Either.right("42")), IdMonad))
  }
}
