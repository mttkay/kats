package com.github.mttkay.kats

import com.github.mttkay.kats.ext.collection.fold
import com.github.mttkay.kats.predef.MulIntMonoid
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class MonoidTest {
  val IntMonoid = Monoid.create(0) { a1: Int, a2: Int -> a1 + a2 }

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

  @Test
  fun `monoid folds on collections`() {
    assertThat(emptyList<Int>().fold()).isEqualTo(0)
    assertThat(listOf(2).fold()).isEqualTo(2)
    assertThat(listOf(2, 4, 6).fold()).isEqualTo(12)

    assertThat(emptyList<String>().fold()).isEqualTo("")
    assertThat(listOf("a").fold()).isEqualTo("a")
    assertThat(listOf("a", "b", "c").fold()).isEqualTo("abc")

    assertThat(emptyList<Int>().fold(MulIntMonoid)).isEqualTo(1)
    assertThat(listOf(2).fold(MulIntMonoid)).isEqualTo(2)
    assertThat(listOf(2, 4, 6).fold(MulIntMonoid)).isEqualTo(48)
  }
}
