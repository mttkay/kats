package com.github.mttkay.kats.laws.applicative

import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.ext.collection.liftList
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class ListApplicativeLaws(override val fa: ListContext<A>,
                          override val fab: ListContext<(A) -> B>,
                          override val fbc: ListContext<(B) -> C>) :
    ApplicativeLaws<ListContext.F, ListContext<A>> {

  companion object {
    @JvmStatic @Parameters(name = "ap({0}, {1})") fun values() = arrayOf(
        arrayOf(listOf(a).liftList(), listOf(::f).liftList(), listOf(::g).liftList()),
        arrayOf(emptyList<A>().liftList(), listOf(::f).liftList(), listOf(::g).liftList()),
        arrayOf(listOf(a).liftList(), emptyList<A>().liftList(), listOf(::g).liftList()),
        arrayOf(emptyList<A>().liftList(), emptyList<A>().liftList(), listOf(::g).liftList())
    )
  }

  override val app = ListApplicative
}
