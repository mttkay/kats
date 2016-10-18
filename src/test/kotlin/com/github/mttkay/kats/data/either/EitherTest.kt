package com.github.mttkay.kats.data.either

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
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
  fun `fmap right bias on Left`() {
    val mapped = leftOutcome.fmap { it.toString() }
    when (mapped) {
      is Either.Left -> assertThat(mapped.value).isEqualTo(Error)
      is Either.Right -> fail("Expected Left outcome")
    }
  }

  @Test
  fun `fmap right bias on Right`() {
    val mapped = rightOutcome.fmap { it.toString() }
    when (mapped) {
      is Either.Left -> fail("Expected Right outcome")
      is Either.Right -> assertThat(mapped.value).isEqualTo("42")
    }
  }
}
