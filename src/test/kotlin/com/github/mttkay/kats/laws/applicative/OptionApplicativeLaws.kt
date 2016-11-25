package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.data.option.OptionApplicative
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class OptionApplicativeLaws(override val fa: Option<A>,
                            override val ffa: Option<(A) -> B>) :
    ApplicativeLaws<Option.F, Option<A>, Option<(A) -> B>> {

  companion object {
    @JvmStatic @Parameters(name = "ap({0}, {1})") fun values() = arrayOf(
        arrayOf(Some(a), Some(::f)),
        arrayOf(None, Some(::f)),
        arrayOf(Some(a), None),
        arrayOf(None, None)
    )
  }

  override val app = OptionApplicative
}
