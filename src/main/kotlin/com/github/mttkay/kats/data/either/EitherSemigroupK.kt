package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.SemigroupK

interface EitherSemigroupK<L> : SemigroupK<EitherF<L>> {

  companion object {
    private val semigroupKInstance = object : EitherSemigroupK<Any> {}

    @Suppress("UNCHECKED_CAST") // this is safe, since we never look at L
    fun <L> instance() = semigroupKInstance as EitherSemigroupK<L>
  }

  override fun <A> combineK(fa1: EitherKind<L, A>, fa2: EitherKind<L, A>): Either<L, A> {
    val left = fa1.narrowEither()
    val right = fa2.narrowEither()
    return when (left) {
      is Either.Right -> left
      is Either.Left -> right
    }
  }
}
