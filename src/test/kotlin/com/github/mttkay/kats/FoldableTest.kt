package com.github.mttkay.kats

import com.github.mttkay.kats.predef.AddIntMonoid
import com.github.mttkay.kats.predef.AddStringMonoid
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

typealias A = Int
typealias B = String

val initial: A = 0
val a: A = 1

abstract class FoldableTest<F, out K : K1<F, A>> {

  abstract val foldable: Foldable<F>

  abstract val fa: K

  val ma: Monoid<A> = AddIntMonoid
  val mb: Monoid<B> = AddStringMonoid

  @Test
  fun `foldLeft`() {
    val fold = foldable.foldLeft(fa, initial) { a1, a2 -> a1 + a2 }

    assertThat(fold).isEqualTo(1)
  }

  @Test
  fun `foldRight`() {
    val fold = foldable.foldRight(fa, initial) { a1, a2 -> a1 + a2 }

    assertThat(fold).isEqualTo(1)
  }

  @Test
  fun `monoid fold`() {
    val fold = foldable.fold(fa, ma)

    assertThat(fold).isEqualTo(1)
  }

  @Test
  fun `foldMap`() {
    val fold = foldable.foldMap(fa, mb, A::toString)

    assertThat(fold).isEqualTo("1")
  }

}
