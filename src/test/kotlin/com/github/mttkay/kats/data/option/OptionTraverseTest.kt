package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListK
import com.github.mttkay.kats.data.list.map
import com.github.mttkay.kats.data.list.narrowList
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionTraverseTest {

  @Test
  fun `traverse None`() {
    val none: Option<ListK<Int>> = None

    val traverse = none.traverse(ListApplicative) {
      it.narrowList() map Int::toString
    }

    assertThat(traverse).isEqualTo(ListK(None))
  }

  @Test
  fun `traverse Some`() {
    val some: Option<ListK<Int>> = Some(ListK(1, 2, 3))

    val traverse = some.traverse(ListApplicative) {
      it.narrowList() map Int::toString
    }

    assertThat(traverse).isEqualTo(ListK(Some("1"), Some("2"), Some("3")))
  }

  @Test
  fun `traverseList`() {
    val some: Option<ListK<Int>> = Some(ListK(1, 2, 3))

    val traverse = some.traverseList { it map Int::toString }

    assertThat(traverse).isEqualTo(ListK(Some("1"), Some("2"), Some("3")))
  }

  @Test
  fun `native traverseList`() {
    val some: Option<List<Int>> = Some(listOf(1, 2, 3))

    val traverse = some.traverseList { it.map(Int::toString) }

    assertThat(traverse).isEqualTo(listOf(Some("1"), Some("2"), Some("3")))
  }

  @Test
  fun `sequence`() {
    val sequence = Some(ListK(1, 2, 3)).sequence(ListApplicative)

    assertThat(sequence).isEqualTo(ListK(Some(1), Some(2), Some(3)))
  }

  @Test
  fun `sequenceList`() {
    val sequence = Some(ListK(1, 2, 3)).sequenceList()

    assertThat(sequence).isEqualTo(ListK(Some(1), Some(2), Some(3)))
  }

  @Test
  fun `native sequenceList`() {
    val sequence = Some(listOf(1, 2, 3)).sequenceList()

    assertThat(sequence).isEqualTo(listOf(Some(1), Some(2), Some(3)))
  }
}
