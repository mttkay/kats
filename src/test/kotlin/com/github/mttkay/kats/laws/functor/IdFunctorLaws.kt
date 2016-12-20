package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.data.id.Id
import com.github.mttkay.kats.data.id.IdFunctor
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class IdFunctorLaws(override val fa: Id<A>) : FunctorLaws<Id.F, Id<A>> {

  companion object {
    @JvmStatic @Parameters(name = "Id: {0}") fun values() = listOf(
        Id(a)
    )
  }

  override val functor = IdFunctor
}
