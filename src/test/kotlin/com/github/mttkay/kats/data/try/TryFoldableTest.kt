package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.FoldableTest
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a

class TryFoldableTest : FoldableTest<Try.F, Try<A>>() {

  override val foldable = TryFoldable

  override val fa = Try.Success(a)
}
