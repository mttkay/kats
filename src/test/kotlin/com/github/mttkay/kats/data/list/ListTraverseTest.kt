package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrowOption
import com.github.mttkay.kats.ext.list.sequenceEither
import com.github.mttkay.kats.ext.list.sequenceOption
import com.github.mttkay.kats.ext.list.traverseOption
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListTraverseTest {

  @Test
  fun `traverse`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val traverse = list.traverse(OptionApplicative) {
      it.narrowOption() map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(ListContext("1", "2", "3")))
  }

  @Test
  fun `traverseOption`() {
    val list = ListContext(Some(1), Some(2), Some(3))

    val traverse: Option<ListContext<String>> = list.traverseOption {
      it map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(ListContext("1", "2", "3")))
  }

  @Test
  fun `native traverseOption`() {
    val list = listOf(Some(1), Some(2), Some(3))

    val traverse: Option<List<String>> = list.traverseOption {
      it map Int::toString
    }

    assertThat(traverse).isEqualTo(Some(listOf("1", "2", "3")))
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

  @Test
  fun `native sequenceOption`() {
    val list = listOf(Some(1), Some(2), Some(3))

    val sequence: Option<List<Int>> = list.sequenceOption()

    assertThat(sequence).isEqualTo(Some(listOf(1, 2, 3)))
  }

  @Test
  fun `sequenceEither`() {
    val list = ListContext(
        Right<Nothing, Int>(1),
        Right<Nothing, Int>(2),
        Right<Nothing, Int>(3)
    )

    val sequence: Either<Nothing, ListContext<Int>> = list.sequenceEither()

    assertThat(sequence).isEqualTo(Right<Nothing, ListContext<Int>>(ListContext(1, 2, 3)))
  }

  @Test
  fun `native sequenceEither`() {
    val list = listOf(
        Right<Nothing, Int>(1),
        Right<Nothing, Int>(2),
        Right<Nothing, Int>(3)
    )

    val sequence: Either<Nothing, List<Int>> = list.sequenceEither()

    assertThat(sequence).isEqualTo(Right<Nothing, List<Int>>(listOf(1, 2, 3)))
  }
}
