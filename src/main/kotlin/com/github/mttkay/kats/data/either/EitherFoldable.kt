package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.Foldable

interface EitherFoldable<in L> : Foldable<EitherF<L>> {

  companion object {

    private val foldableInstance = object : EitherFoldable<Any> {}

    @Suppress("UNCHECKED_CAST")
    fun <L> instance() = foldableInstance as EitherFoldable<L>
  }

  override fun <A, B> foldLeft(fa: EitherKind<L, A>, b: B, f: (B, A) -> B): B {
    val either = fa.narrowEither()
    return when (either) {
      is Either.Left -> b
      is Either.Right -> f(b, either.value)
    }
  }

  override fun <A, B> foldRight(fa: EitherKind<L, A>, b: B, f: (A, B) -> B): B {
    val either = fa.narrowEither()
    return when (either) {
      is Either.Left -> b
      is Either.Right -> f(either.value, b)
    }
  }
}

fun <L, R, S> Either<L, R>.foldLeft(s: S, f: (S, R) -> S): S = EitherFoldable.instance<L>().foldLeft(this, s, f)

fun <L, R, S> Either<L, R>.foldRight(s: S, f: (R, S) -> S): S = EitherFoldable.instance<L>().foldRight(this, s, f)
