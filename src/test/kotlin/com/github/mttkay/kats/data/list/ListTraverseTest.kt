package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrow
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListTraverseTest {

  @Test
  fun `traverse`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val traverse = list.traverse(OptionApplicative) {
      it.narrow() fmap Int::toString
    }

    assertThat(traverse).isEqualTo(Some(ListContext("1", "2", "3")))
  }

  @Test
  fun `traverseOption`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val traverse: Option<ListContext<String>> = list.traverseOption {
      it fmap Int::toString
    }

    assertThat(traverse).isEqualTo(Some(ListContext("1", "2", "3")))
  }

  @Test
  fun `sequence Some`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val sequence: K1<Option.F, ListContext<Int>> = list.sequence(OptionApplicative)

    assertThat(sequence).isEqualTo(Some(ListContext(1, 2, 3)))
  }

  @Test
  fun `sequence None`() {
    val list = ListContext(Some(1), None, Some(3))

    val sequence: K1<Option.F, ListContext<Int>> = list.sequence(OptionApplicative)

    assertThat(sequence).isEqualTo(None)
  }

  @Test
  fun `sequenceOption`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val sequence: Option<ListContext<Int>> = list.sequenceOption()

    assertThat(sequence).isEqualTo(Some(ListContext(1, 2, 3)))
  }

}
