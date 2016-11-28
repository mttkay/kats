package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTest {

  object Error

  val leftOutcome: Either<Error, Int> = Left(Error)
  val rightOutcome: Either<Error, Int> = Right(42)

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
    assertThat(Right<Error, Int>(42)).isEqualTo(Right<Error, Int>(42))
  }

  @Test // TODO: use proper equivalence tester
  fun `left value equality`() {
    assertThat(Left<Error, Int>(Error)).isEqualTo(Left<Error, Int>(Error))
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
