package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherApplicativeTest {

  object Error

  val app = EitherApplicative.instance<Error>()

  private fun <R> left() = Left<Error, R>(Error)

  private fun <R> right(a: R) = Right<Error, R>(a)

  @Test
  fun `pure`() {
    assertThat(app.pure(42)).isEqualTo(right(42))
  }

  @Test
  fun `ap right right = right`() {
    val fa = right(42)
    val ffa = right({ n: Int -> n * 2 })

    assertThat(app.ap(fa, ffa)).isEqualTo(right(84))
  }

  @Test
  fun `ap left right = left`() {
    val fa = left<Int>()
    val ffa = right({ n: Int -> n * 2 })

    assertThat(app.ap(fa, ffa)).isEqualTo(fa)
  }

  @Test
  fun `ap right left = left`() {
    val fa = right(42)
    val ffa = left<(Int) -> Int>()

    assertThat(app.ap(fa, ffa)).isEqualTo(ffa)
  }

  @Test
  fun `ap left left = left`() {
    val fa = left<Int>()
    val ffa = left<(Int) -> Int>()

    assertThat(app.ap(fa, ffa)).isEqualTo(fa)
  }
}
