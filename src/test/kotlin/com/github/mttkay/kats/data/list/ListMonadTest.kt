package com.github.mttkay.kats.data.list

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListMonadTest {

  @Test
  fun `pure`() {
    assertThat(ListMonad.pure(42)).isEqualTo(ListContext(42))
  }

  @Test
  fun `ap`() {
    val fa = ListContext(1, 2, 3)
    val ffa = ListContext(
        { n: Int -> n * 2 },
        { n: Int -> n + 1 }
    )

    val fb = ListMonad.ap(fa, ffa)

    assertThat(fb).isEqualTo(
        ListContext(2, 4, 6, 2, 3, 4)
    )
  }

  @Test
  fun `flatMap`() {
    val fa = ListContext(1, 2, 3)

    val fb = ListMonad.flatMap(fa) { ListContext(it * 2) }

    assertThat(fb).isEqualTo(ListContext(2, 4, 6))
  }

  @Test
  fun `flatten`() {
    val fa = ListContext(ListContext(1, 2, 3))

    val fb = ListMonad.flatten(fa)

    assertThat(fb).isEqualTo(ListContext(1, 2, 3))
  }
}
