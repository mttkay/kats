package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.ext.collection.liftList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListApplicativeTest {

  @Test
  fun `pure`() {
    assertThat(ListApplicative.pure(42)).isEqualTo(ListContext(listOf(42)))
  }

  @Test
  fun `ap on ListContext`() {
    val fa = ListContext(listOf(1, 2, 3))
    val ffa = ListContext(listOf(
        { n: Int -> n * 2 },
        { n: Int -> n + 1 }
    ))

    assertThat(ListApplicative.ap(fa, ffa)).isEqualTo(
        ListContext(listOf(2, 4, 6, 2, 3, 4))
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
    val fa = ListContext(listOf(1, 2, 3))

    val mapped = ListApplicative.map(fa) { it * 2 }

    assertThat(mapped).isEqualTo(ListContext(listOf(2, 4, 6)))
  }

  @Test
  fun `product`() {
    val left = ListContext(listOf(1, 2))
    val right = ListContext(listOf(3, 4))

    val product = ListApplicative.product(left, right)

    assertThat(product).isEqualTo(listOf(
        Pair(1, 3), Pair(1, 4), Pair(2, 3), Pair(2, 4)
    ).liftList())
  }
}
