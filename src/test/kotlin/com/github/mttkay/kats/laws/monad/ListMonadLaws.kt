package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.list.ListMonad
import com.github.mttkay.kats.laws.applicative.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ListMonadLaws(override val fa: ListContext<A>,
                    override val fab: ListContext<(A) -> B>) :
    MonadLaws<ListContext.F, ListContext<A>>(ListMonad) {

  companion object {
    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(ListContext(a), ListContext(::f)),
        arrayOf(ListContext(), ListContext(::f)),
        arrayOf(ListContext(a), ListContext()),
        arrayOf(ListContext(), ListContext())
    )
  }

  override val fbc: ListContext<(B) -> C> = ListContext(::g)

  override val mf: (A) -> ListContext<B> = { ListContext(f(it)) }
  override val mg: (B) -> ListContext<C> = { ListContext(g(it)) }
}
