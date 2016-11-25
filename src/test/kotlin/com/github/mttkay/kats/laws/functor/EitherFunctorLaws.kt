package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.EitherF
import com.github.mttkay.kats.data.either.EitherFunctor
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

private typealias L = String

@RunWith(Parameterized::class)
class EitherFunctorLaws(override val fa: Either<L, A>) : FunctorLaws<EitherF<L>, Either<L, A>> {

  companion object {
    @JvmStatic @Parameters(name = "Either: {0}") fun values() = arrayOf(
        Either.Right<Any, A>(a),
        Either.Left<Any, A>(Object())
    )
  }

  override val functor = EitherFunctor.instance<L>()
}
