package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.FoldableTest
import com.github.mttkay.kats.test.A
import com.github.mttkay.kats.test.a

object L
typealias R = A

class EitherFoldableTest : FoldableTest<EitherF<L>, Either<L, R>>() {

  override val foldable = EitherFoldable.instance<L>()

  override val fa = Either.Right<L, R>(a)
}
