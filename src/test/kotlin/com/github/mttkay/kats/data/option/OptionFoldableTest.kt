package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.A
import com.github.mttkay.kats.FoldableTest
import com.github.mttkay.kats.a

class OptionFoldableTest : FoldableTest<Option.F, Option<A>>() {

  override val foldable = OptionFoldable

  override val fa = Option.Some(a)

}
