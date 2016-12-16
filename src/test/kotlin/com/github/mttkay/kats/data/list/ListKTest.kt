package com.github.mttkay.kats.data.list

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ListKTest {

  val list = ListK(1, 2, 3)

  @Test
  fun `map`() {
    assertThat(list.map { it * 2 }).isEqualTo(ListK(2, 4, 6))
  }

  @Test
  fun `flatMap`() {
    assertThat(list.flatMap { ListK(it * 2) }).isEqualTo(ListK(2, 4, 6))
  }

  @Test
  fun `foldLeft`() {
    assertThat(list.foldLeft(0) { b, a -> a - b }).isEqualTo(2)
  }

  @Test
  fun `foldRight`() {
    assertThat(list.foldRight(0) { a, b -> a - b }).isEqualTo(2)
  }

}
