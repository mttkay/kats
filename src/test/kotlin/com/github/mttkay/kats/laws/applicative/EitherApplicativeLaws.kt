package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.either.EitherApplicative
import com.github.mttkay.kats.data.either.EitherF
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

private typealias L = String

@RunWith(Parameterized::class)
class EitherApplicativeLaws(override val fa: Either<L, A>,
                            override val fab: Either<L, (A) -> B>) :
    ApplicativeLaws<EitherF<L>, Either<L, A>> {

  companion object {
    @JvmStatic @Parameters(name = "ap({0}, {1})") fun values() = arrayOf(
        arrayOf(Right<L, A>(a), Right<L, (A) -> B>(::f)),
        arrayOf(Left<L, A>("fail"), Right<L, (A) -> B>(::f)),
        arrayOf(Right<L, A>(a), Left<L, (A) -> B>("fail")),
        arrayOf(Left<L, A>("fail"), Left<L, (A) -> B>("fail"))
    )
  }

  override val fbc: Either<L, (B) -> C> = Right<L, (B) -> C>(::g)

  override val app = EitherApplicative.instance<L>()
}
