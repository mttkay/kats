package com.github.mttkay.kats

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MonoidTest {
  val IntMonoid = Monoid.of(0) { a1: Int, a2: Int -> a1 + a2 }

  @Test
  fun `combine`() {
    assertThat(IntMonoid.combine(1, 2)).isEqualTo(3)
  }

  @Test
  fun `combine all with zero items`() {
    assertThat(IntMonoid.combineAll(emptyList())).isEqualTo(0)
  }

  @Test
  fun `combine all with single item`() {
    assertThat(IntMonoid.combineAll(listOf(1))).isEqualTo(1)
  }

  @Test
  fun `combine all with single null item`() {
    assertThat(IntMonoid.combineAll(listOf(null))).isEqualTo(0)
  }

  @Test
  fun `combine all with N items`() {
    assertThat(IntMonoid.combineAll(listOf(1, 2))).isEqualTo(3)
  }

  @Test
  fun `combine all with N items and some null`() {
    assertThat(IntMonoid.combineAll(listOf(1, null))).isEqualTo(1)
  }

  @Test
  fun `maybe combine`() {
    assertThat(IntMonoid.maybeCombine(1, 2)).isEqualTo(3)
    assertThat(IntMonoid.maybeCombine(1, null)).isEqualTo(1)
    assertThat(IntMonoid.maybeCombine(null, 2)).isEqualTo(2)
    assertThat(IntMonoid.maybeCombine(null, null)).isNull()
  }
}
