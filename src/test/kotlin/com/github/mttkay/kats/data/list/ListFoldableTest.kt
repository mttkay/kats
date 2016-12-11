package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.FoldableTest
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a

class ListFoldableTest : FoldableTest<ListContext.F, ListContext<A>>() {

  override val foldable = ListFoldable

  override val fa = ListContext(a)
}
