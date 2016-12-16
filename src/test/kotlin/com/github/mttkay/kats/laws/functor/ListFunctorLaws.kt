package com.github.mttkay.kats.laws.functor

import com.github.mttkay.kats.data.list.ListK
import com.github.mttkay.kats.data.list.ListFunctor
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ListFunctorLaws(override val fa: ListK<A>) :
    FunctorLaws<ListK.F, ListK<A>> {

  companion object {
    @JvmStatic @Parameters(name = "List: {0}") fun values() = listOf(
        ListK(listOf(a)),
        ListK(emptyList<A>())
    )
  }

  override val functor = ListFunctor
}
