package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.FunctorTest
import com.github.mttkay.kats.ext.list.fproduct
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListFunctorTest : FunctorTest<ListK.F, ListK<A>>() {

  override val functor = ListFunctor

  override val fa = ListK(a)

  @Test
  fun `map`() {
    val mapped = ListK(1, 2, 3).map(Int::toString)

    assertThat(mapped.list).containsExactly("1", "2", "3")
  }

  @Test
  fun `native fproduct`() {
    val fprod = listOf(1, 2, 3).fproduct(Int::toString)

    assertThat(fprod).isEqualTo(listOf(
        Pair(1, "1"),
        Pair(2, "2"),
        Pair(3, "3")
    ))
  }
}
