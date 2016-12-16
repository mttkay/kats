package com.github.mttkay.kats.data.list

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListMonadTest {

  @Test
  fun `pure`() {
    assertThat(ListMonad.pure(42)).isEqualTo(ListK(42))
  }

  @Test
  fun `ap`() {
    val fa = ListK(1, 2, 3)
    val ffa = ListK(
        { n: Int -> n * 2 },
        { n: Int -> n + 1 }
    )

    val fb = ListMonad.ap(fa, ffa)

    assertThat(fb).isEqualTo(
        ListK(2, 4, 6, 2, 3, 4)
    )
  }

  @Test
  fun `flatMap`() {
    val fa = ListK(1, 2, 3)

    val fb = ListMonad.flatMap(fa) { ListK(it * 2) }

    assertThat(fb).isEqualTo(ListK(2, 4, 6))
  }

  @Test
  fun `flatten`() {
    val fa = ListK(ListK(1, 2, 3))

    val fb = ListMonad.flatten(fa)

    assertThat(fb).isEqualTo(ListK(1, 2, 3))
  }
}
