package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.FunctorTest
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListFunctorTest : FunctorTest<ListK.F, ListK<A>>() {

  override val functor = ListFunctor

  override val fa = ListK(a)

  @Test
  fun `map maps lists in ListContext`() {
    val ctx: ListK<String> = ListK(1, 2, 3).map(Int::toString)

    assertThat(ctx.list).containsExactly("1", "2", "3")
  }

}
