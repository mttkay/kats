package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.data.option.OptionMonad
import com.github.mttkay.kats.laws.applicative.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class OptionMonadLaws(override val fa: Option<A>,
                      override val fab: Option<(A) -> B>) :
    MonadLaws<Option.F, Option<A>>(OptionMonad) {

  companion object {
    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(Some(a), Option.Some(::f)),
        arrayOf(None, Option.Some(::f)),
        arrayOf(Some(a), None),
        arrayOf(None, None)
    )
  }

  override val fbc: Option<(B) -> C> = Option.Some(::g)
  override val mf: (A) -> Option<B> = { Some(f(it)) }
  override val mg: (B) -> Option<C> = { Some(g(it)) }
}
