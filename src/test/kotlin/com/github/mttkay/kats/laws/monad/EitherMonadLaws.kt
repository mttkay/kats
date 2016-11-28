package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.either.EitherF
import com.github.mttkay.kats.data.either.EitherMonad
import com.github.mttkay.kats.laws.applicative.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

private typealias L = String

@RunWith(Parameterized::class)
class EitherMonadLaws(override val fa: Either<L, A>,
                      override val fab: Either<L, (A) -> B>) :
    MonadLaws<EitherF<L>, Either<L, A>>(EitherMonad.instance()) {
  companion object {
    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(Right<L, A>(a), Right<L, (A) -> B>(::f)),
        arrayOf(Left<L, A>("fail"), Right<L, (A) -> B>(::f)),
        arrayOf(Right<L, A>(a), Left<L, (A) -> B>("fail")),
        arrayOf(Left<L, A>("fail"), Left<L, (A) -> B>("fail"))
    )
  }

  override val fbc: Either<L, (B) -> C> = Right<L, (B) -> C>(::g)
  override val mf: (A) -> Either<L, B> = { Right(f(it)) }
  override val mg: (B) -> Either<L, C> = { Right(g(it)) }

}
