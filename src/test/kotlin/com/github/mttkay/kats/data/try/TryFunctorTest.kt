package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TryFunctorTest {

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
