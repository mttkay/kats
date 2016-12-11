package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.FunctorTest
import com.github.mttkay.kats.test.a
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.fail
import org.junit.Test

class EitherFunctorTest : FunctorTest<EitherF<L>, Either<L, R>>() {

  override val functor = EitherFunctor.instance<L>()

  override val fa = Either.Right<L, R>(a)

  val leftOutcome: Either<L, Int> = Either.Left(L)
  val rightOutcome: Either<L, Int> = Either.Right(42)

  @Test
  fun `map right bias on Left`() {
    val mapped = leftOutcome.map(Int::toString)
    when (mapped) {
      is Either.Left -> assertThat(mapped.value).isEqualTo(L)
      is Either.Right -> fail("Expected Left outcome")
    }
  }

  @Test
  fun `map right bias on Right`() {
    val mapped = rightOutcome.map(Int::toString)
    when (mapped) {
      is Either.Left -> fail("Expected Right outcome")
      is Either.Right -> assertThat(mapped.value).isEqualTo("42")
    }
  }
}
