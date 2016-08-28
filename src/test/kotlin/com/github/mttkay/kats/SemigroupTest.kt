package com.github.mttkay.kats

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SemigroupTest {
  val IntSemigroup = Semigroup.create { a1: Int, a2: Int -> a1 + a2 }

  @Test
  fun `combine`() {
    assertThat(IntSemigroup.combine(1, 2)).isEqualTo(3)
  }

  @Test
  fun `combine all with zero items`() {
    assertThat(IntSemigroup.combineAll(emptyList())).isNull()
  }

  @Test
  fun `combine all with single item`() {
    assertThat(IntSemigroup.combineAll(listOf(1))).isEqualTo(1)
  }

  @Test
  fun `combine all with N items`() {
    assertThat(IntSemigroup.combineAll(listOf(1, 2))).isEqualTo(3)
  }

  @Test
  fun `combine all with N items and some null`() {
    assertThat(IntSemigroup.combineAll(listOf(1, null))).isEqualTo(1)
  }

  @Test
  fun `maybe combine`() {
    assertThat(IntSemigroup.maybeCombine(1, 2)).isEqualTo(3)
    assertThat(IntSemigroup.maybeCombine(1, null)).isEqualTo(1)
    assertThat(IntSemigroup.maybeCombine(null, 2)).isEqualTo(2)
    assertThat(IntSemigroup.maybeCombine(null, null)).isNull()
  }
}
