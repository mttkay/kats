package com.github.mttkay.kats.data.either

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTest {

  object Error

  val leftOutcome: Either<Error, Nothing> = Either.left(Error)
  val rightOutcome: Either<Nothing, Int> = Either.right(42)

  @Test
  fun `isLeft and isRight on Left`() {
    assertThat(leftOutcome.isLeft).isTrue()
    assertThat(leftOutcome.isRight).isFalse()
  }

  @Test
  fun `isLeft and isRight on Right`() {
    assertThat(rightOutcome.isLeft).isFalse()
    assertThat(rightOutcome.isRight).isTrue()
  }

  @Test // TODO: use proper equivalence tester
  fun `right value equality`() {
    assertThat(Either.right(42)).isEqualTo(Either.right(42))
  }

  @Test // TODO: use proper equivalence tester
  fun `left value equality`() {
    assertThat(Either.left(Error)).isEqualTo(Either.left(Error))
  }

  @Test
  fun `foldLeft for Left`() {
    assertThat(leftOutcome.foldLeft("0") { b, a -> b + a }).isEqualTo("0")
  }

  @Test
  fun `foldLeft for Right`() {
    assertThat(rightOutcome.foldLeft("0") { b, a -> b + a }).isEqualTo("042")
  }

  @Test
  fun `foldRight for Left`() {
    assertThat(leftOutcome.foldRight("0") { a, b -> b + a }).isEqualTo("0")
  }

  @Test
  fun `foldRight for Right`() {
    assertThat(rightOutcome.foldRight("0") { a, b -> b + a }).isEqualTo("042")
  }
}
