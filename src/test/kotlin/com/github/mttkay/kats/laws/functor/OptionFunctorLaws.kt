package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.OptionFunctor
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class OptionFunctorLaws(override val fa: Option<A>) : FunctorLaws<Option.F, Option<A>> {

  companion object {
    @JvmStatic @Parameters(name = "Option: {0}") fun values() = listOf(
        Option.Some(a),
        Option.None
    )
  }

  override val functor = OptionFunctor
}
