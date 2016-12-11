package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.FunctorTest
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TryFunctorTest : FunctorTest<Try.F, Try<A>>() {

  override val functor = TryFunctor

  override val fa = Success(a)

  @Test
  fun `map Success`() {
    val success = Success(42)

    val result = success map Int::toString

    assertThat(result).isEqualTo(Success("42"))
  }

  @Test
  fun `map Failure`() {
    val failure = Failure<Int>(Exception())

    val result = failure map Int::toString

    assertThat(result).isEqualTo(failure)
  }

}
