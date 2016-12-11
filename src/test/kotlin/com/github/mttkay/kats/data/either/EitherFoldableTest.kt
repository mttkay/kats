package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.A
import com.github.mttkay.kats.FoldableTest
import com.github.mttkay.kats.a

typealias L = Nothing
typealias R = A

class EitherFoldableTest : FoldableTest<EitherF<L>, Either<L, R>>() {

  override val foldable = EitherFoldable.instance<L>()

  override val fa = Either.Right<L, R>(a)
}
