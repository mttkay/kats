package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.list.map
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.map
import com.github.mttkay.kats.data.option.narrowOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EitherTraverseTest {

  val either = EitherBuilder<Int, Option<Int>>()

  @Test
  fun `traverse Left`() {
    val traverse = either.left(0).traverse(OptionApplicative) {
      it.narrowOption() map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(either.left(0)))
  }

  @Test
  fun `traverse Right`() {
    val traverse = either.right(Some(42)).traverse(OptionApplicative) {
      it.narrowOption() map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(Right<Int, String>("42")))
  }

  @Test
  fun `traverseOption`() {
    val traverse = Right<Nothing, Option<Int>>(Some(42)).traverseOption {
      it map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(Right<Nothing, String>("42")))
  }

  @Test
  fun `traverseList`() {
    val traverse = Right<Nothing, ListContext<Int>>(ListContext(1, 2, 3)).traverseList {
      it map Int::toString
    }

    assertThat(traverse).isEqualTo(ListContext(
        Right<Nothing, String>("1"),
        Right<Nothing, String>("2"),
        Right<Nothing, String>("3")
    ))
  }

  @Test
  fun `native traverseList`() {
    val traverse = Right<Nothing, List<Int>>(listOf(1, 2, 3)).traverseList {
      it.map(Int::toString)
    }

    assertThat(traverse).isEqualTo(listOf(
        Right<Nothing, String>("1"),
        Right<Nothing, String>("2"),
        Right<Nothing, String>("3")
    ))
  }

  @Test
  fun `sequenceOption`() {
    val sequence = Right<Nothing, Option<Int>>(Some(42)).sequenceOption()

    assertThat(sequence).isEqualTo(Some(Right<Nothing, Int>(42)))
  }

  @Test
  fun `sequenceList`() {
    val sequence = Right<Nothing, ListContext<Int>>(ListContext(1, 2, 3)).sequenceList()

    assertThat(sequence).isEqualTo(ListContext(
        Right<Nothing, Int>(1),
        Right<Nothing, Int>(2),
        Right<Nothing, Int>(3)
    ))
  }

  @Test
  fun `native sequenceList`() {
    val sequence = Right<Nothing, List<Int>>(listOf(1, 2, 3)).sequenceList()

    assertThat(sequence).isEqualTo(listOf(
        Right<Nothing, Int>(1),
        Right<Nothing, Int>(2),
        Right<Nothing, Int>(3)
    ))
  }

}
