package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.list.narrowList
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OptionTraverseTest {

  @Test
  fun `traverse None`() {
    val none: Option<ListContext<Int>> = None

    val traverse = none.traverse(ListApplicative) {
      it.narrowList() fmap Int::toString
    }

    assertThat(traverse).isEqualTo(ListContext(None))
  }

  @Test
  fun `traverse Some`() {
    val some: Option<ListContext<Int>> = Some(ListContext(1, 2, 3))

    val traverse = some.traverse(ListApplicative) {
      it.narrowList() fmap Int::toString
    }

    assertThat(traverse).isEqualTo(ListContext(Some("1"), Some("2"), Some("3")))
  }

  @Test
  fun `traverseList`() {
    val some: Option<ListContext<Int>> = Some(ListContext(1, 2, 3))

    val traverse = some.traverseList { it fmap Int::toString }

    assertThat(traverse).isEqualTo(ListContext(Some("1"), Some("2"), Some("3")))
  }

  @Test
  fun `sequence`() {
    val sequence = Some(ListContext(1, 2, 3)).sequence(ListApplicative)

    assertThat(sequence).isEqualTo(ListContext(Some(1), Some(2), Some(3)))
  }

  @Test
  fun `sequenceList`() {
    val sequence = Some(ListContext(1, 2, 3)).sequenceList()

    assertThat(sequence).isEqualTo(ListContext(Some(1), Some(2), Some(3)))
  }
}
