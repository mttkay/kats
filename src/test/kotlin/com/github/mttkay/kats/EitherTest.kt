package com.github.mttkay.kats

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTest {

  object Error

  val leftOutcome: Either<Error, Int> = Either.Left(Error)
  val rightOutcome: Either<Error, Int> = Either.Right(42)


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

  @Test
  fun `map right bias on Left`() {
    val mapped = leftOutcome.map { it.toString() }
    assertThat(mapped.value).isEqualTo(Error)
  }

  @Test
  fun `map right bias on Right`() {
    val mapped = rightOutcome.map { it.toString() }
    assertThat(mapped.value).isEqualTo("42")
  }
}
