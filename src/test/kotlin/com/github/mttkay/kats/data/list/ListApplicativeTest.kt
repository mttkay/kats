package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.ext.collection.toListK
import com.github.mttkay.kats.ext.list.times
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListApplicativeTest {

  @Test
  fun `pure`() {
    assertThat(ListApplicative.pure(42)).isEqualTo(ListK(42))
  }

  @Test
  fun `ap on ListContext`() {
    val fa = ListK(listOf(1, 2, 3))
    val ffa = ListK(listOf(
        { n: Int -> n * 2 },
        { n: Int -> n + 1 }
    ))

    assertThat(ListApplicative.ap(fa, ffa)).isEqualTo(
        ListK(listOf(2, 4, 6, 2, 3, 4))
    )
  }

  @Test
  fun `ap on List`() {
    val fa = listOf(1, 2, 3)
    val ffa = listOf(
        { n: Int -> n * 2 },
        { n: Int -> n + 1 }
    )

    assertThat(ListApplicative.ap(fa, ffa)).isEqualTo(
        listOf(2, 4, 6, 2, 3, 4)
    )
  }

  @Test
  fun `map`() {
    val fa = ListK(listOf(1, 2, 3))

    val mapped = fa map { it * 2 }

    assertThat(mapped).isEqualTo(ListK(listOf(2, 4, 6)))
  }

  @Test
  fun `product`() {
    val left = ListK(listOf(1, 2))
    val right = ListK(listOf(3, 4))

    val product = left * right

    assertThat(product).isEqualTo(listOf(
        Pair(1, 3), Pair(1, 4), Pair(2, 3), Pair(2, 4)
    ).toListK())
  }

  @Test
  fun `native product`() {
    val left = listOf(1, 2)
    val right = listOf(3, 4)

    val product = left * right

    assertThat(product).isEqualTo(listOf(
        Pair(1, 3), Pair(1, 4), Pair(2, 3), Pair(2, 4)
    ))
  }
}
