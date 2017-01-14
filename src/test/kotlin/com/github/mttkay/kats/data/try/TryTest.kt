package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TryTest {

  val error = RuntimeException("boom")

  @Test
  fun `try success`() {
    val result = Try.run(42) { it * 2 }

    assertThat(result).isEqualTo(Success(84))
  }

  @Test
  fun `try failure`() {
    val result = Try.run(42) { throw error }

    assertThat(result).isEqualTo(Failure<Int>(error))
  }

  @Test
  fun `get success`() {
    assertThat(Success(42).get).isEqualTo(42)
  }

  @Test(expected = RuntimeException::class)
  fun `get failure`() {
    Failure<Int>(error).get
  }

  @Test
  fun `getOrElse success`() {
    assertThat(Success(42).getOrElse { 0 }).isEqualTo(42)
  }

  @Test
  fun `getOrElse failure`() {
    assertThat(Failure<Int>(error).getOrElse { 0 }).isEqualTo(0)
  }

  @Test
  fun `lift throwing function`() {
    val f = { throw error }
    assertThat(Try.lift(f).invoke()).isEqualTo(Failure<Nothing>(error))
  }
}
