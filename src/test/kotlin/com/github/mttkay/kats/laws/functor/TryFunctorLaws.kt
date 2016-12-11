package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.data.`try`.Try
import com.github.mttkay.kats.data.`try`.TryFunctor
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class TryFunctorLaws(override val fa: Try<A>) : FunctorLaws<Try.F, Try<A>> {

  companion object {

    val error = Exception("error")

    @JvmStatic @Parameters(name = "Try: {0}") fun values() = listOf(
        Try.Success(a),
        Try.Failure<A>(error)
    )
  }

  override val functor = TryFunctor
}
