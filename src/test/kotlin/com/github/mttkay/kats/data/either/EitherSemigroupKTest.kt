package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.either.EitherSemigroupK.Companion.instance
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherSemigroupKTest {

  interface Error
  object Err1 : Error
  object Err2 : Error

  @Test
  fun `combineK returns left when left is Right and right is Left`() {
    val combined = instance<Error>().combineK(Right(42), Left(Err1))

    assertThat(combined).isEqualTo(Right<Error, Int>(42))
  }

  @Test
  fun `combineK returns right when left is Left and right is Right`() {
    val combined = instance<Error>().combineK(Left(Err1), Right(42))

    assertThat(combined).isEqualTo(Right<Error, Int>(42))
  }

  @Test
  fun `combineK returns left when both are Right`() {
    val combined = instance<Error>().combineK(Right(44), Right(42))

    assertThat(combined).isEqualTo(Right<Error, Int>(44))
  }

  @Test
  fun `combineK returns right when both are Left`() {
    val combined = instance<Error>().combineK(Left<Error, Int>(Err1), Left<Error, Int>(Err2))

    assertThat(combined).isEqualTo(Left<Error, Int>(Err2))
  }

}
