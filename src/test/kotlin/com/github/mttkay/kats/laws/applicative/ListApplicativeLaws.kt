package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListContext
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ListApplicativeLaws(override val fa: ListContext<A>,
                          override val ffa: ListContext<(A) -> B>) :
    ApplicativeLaws<ListContext.F, ListContext<A>, ListContext<(A) -> B>> {

  companion object {
    @JvmStatic @Parameters(name = "List: {0}") fun values() = arrayOf(
        arrayOf(ListContext(listOf(a)), ListContext(listOf(::f))),
        arrayOf(ListContext(emptyList<A>()), ListContext(listOf(::f)))
    )
  }

  override val app = ListApplicative
}
