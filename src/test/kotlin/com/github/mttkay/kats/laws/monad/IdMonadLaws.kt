package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.id.Id
import com.github.mttkay.kats.data.id.IdMonad
import com.github.mttkay.kats.test.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class IdMonadLaws(override val fa: Id<A>,
                  override val fab: Id<(A) -> B>) :
    MonadLaws<Id.F, Id<A>>(IdMonad) {

  companion object {
    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(Id(a), Id(::f))
    )
  }

  override val fbc: Id<(B) -> C> = Id(::g)
  override val mf: (A) -> Id<B> = { Id(f(it)) }
  override val mg: (B) -> Id<C> = { Id(g(it)) }
}
