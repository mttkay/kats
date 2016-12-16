package com.github.mttkay.kats.laws.monad

import com.github.mttkay.kats.data.list.ListK
import com.github.mttkay.kats.data.list.ListMonad
import com.github.mttkay.kats.test.*
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ListMonadLaws(override val fa: ListK<A>,
                    override val fab: ListK<(A) -> B>) :
    MonadLaws<ListK.F, ListK<A>>(ListMonad) {

  companion object {
    @JvmStatic @Parameters(name = "fa = {0}, fab = {1}") fun values() = arrayOf(
        arrayOf(ListK(a), ListK(::f)),
        arrayOf(ListK(), ListK(::f)),
        arrayOf(ListK(a), ListK()),
        arrayOf(ListK(), ListK())
    )
  }

  override val fbc: ListK<(B) -> C> = ListK(::g)

  override val mf: (A) -> ListK<B> = { ListK(f(it)) }
  override val mg: (B) -> ListK<C> = { ListK(g(it)) }
}
