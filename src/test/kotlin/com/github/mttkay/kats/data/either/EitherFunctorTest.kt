package com.github.mttkay.kats.data.either

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test

class EitherFunctorTest {

  object Error

  val leftOutcome: Either<Error, Int> = Either.Left(Error)
  val rightOutcome: Either<Error, Int> = Either.Right(42)

  @Test
  fun `fmap right bias on Left`() {
    val mapped = leftOutcome.fmap(Int::toString)
    when (mapped) {
      is Either.Left -> assertThat(mapped.value).isEqualTo(Error)
      is Either.Right -> fail("Expected Left outcome")
    }
  }

  @Test
  fun `fmap right bias on Right`() {
    val mapped = rightOutcome.fmap(Int::toString)
    when (mapped) {
      is Either.Left -> fail("Expected Right outcome")
      is Either.Right -> assertThat(mapped.value).isEqualTo("42")
    }
  }
}