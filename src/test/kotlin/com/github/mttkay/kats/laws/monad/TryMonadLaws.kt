package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.`try`.Try
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success
import com.github.mttkay.kats.data.`try`.TryMonad
import com.github.mttkay.kats.test.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class TryMonadLaws(override val fa: Try<A>,
                   override val fab: Try<(A) -> B>) :
    MonadLaws<Try.F, Try<A>>(TryMonad) {

  companion object {
    val error = Exception("error")

    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(Success(a), Success(::f)),
        arrayOf(Failure<A>(error), Success(::f)),
        arrayOf(Success(a), Failure<A>(error)),
        arrayOf(Failure<A>(error), Failure<A>(error))
    )
  }

  override val fbc: Try<(B) -> C> = Success(::g)
  override val mf: (A) -> Try<B> = { Success(f(it)) }
  override val mg: (B) -> Try<C> = { Success(g(it)) }
}
