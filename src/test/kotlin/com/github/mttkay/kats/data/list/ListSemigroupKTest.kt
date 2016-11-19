package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.ext.collection.liftList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListSemigroupKTest {

  val list1 = listOf(1, 2, 3).liftList()
  val list2 = listOf(4, 5, 6).liftList()

  @Test
  fun `combineK`() {
    val combined = ListSemigroupK.combineK(list1, list2).list

    assertThat(combined).containsExactly(1, 2, 3, 4, 5, 6)
  }

  @Test
  fun `algebra`() {
    val algebra = ListSemigroupK.algebra<Int>()

    val combined = algebra.combine(list1, list2)

    assertThat(combined).isEqualTo(listOf(1, 2, 3, 4, 5, 6).liftList())
  }

}
