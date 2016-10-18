package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.ext.collection.liftList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListContextTest {

  val liftedList = listOf(1, 2, 3).liftList()

  @Test
  fun `fmap maps lists in ListContext`() {
    val ctx: ListContext<String> = liftedList.fmap { it.toString() }

    assertThat(ctx.list).containsExactly("1", "2", "3")
  }

  @Test
  fun `map unlifts list from ListContext`() {
    val list: List<String> = liftedList.map { it.toString() }

    assertThat(list).containsExactly("1", "2", "3")
  }

}
