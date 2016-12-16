package com.github.mttkay.kats.data.either

import com.github.mttkay.kats.*
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListK
import com.github.mttkay.kats.data.list.ListKind
import com.github.mttkay.kats.data.list.narrowList
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrowOption
import com.github.mttkay.kats.ext.collection.toListK

class EitherTraverse<L> :
    Traverse<EitherF<L>>,
    Foldable<EitherF<L>> by EitherFoldable.instance(),
    Functor<EitherF<L>> by EitherFunctor.instance() {

  companion object {

    private val traverseInstance = EitherTraverse<Any>()

    @Suppress("UNCHECKED_CAST")
    fun <L> instance(): EitherTraverse<L> = traverseInstance as EitherTraverse<L>
  }

  override fun <G, A, B> traverse(fa: EitherKind<L, A>, app: Applicative<G>, f: (A) -> K1<G, B>): K1<G, Either<L, B>> {
    val either = fa.narrowEither()
    return when (either) {
      is Left -> app.pure(either.rightCast())
      is Right -> app.map(f(either.value)) { Right<L, B>(it) }
    }
  }
}

fun <L, A, B, G> Either<L, K1<G, A>>.traverse(app: Applicative<G>, f: (K1<G, A>) -> K1<G, B>): K1<G, Either<L, B>> =
    EitherTraverse.instance<L>().traverse(this, app, f)

fun <L, A, B> Either<L, ListK<A>>.traverseList(f: (ListK<A>) -> ListK<B>): ListK<Either<L, B>> =
    traverse(ListApplicative) {
      f(it.narrowList())
    }.narrowList()

fun <L, A, B> Either<L, List<A>>.traverseList(f: (List<A>) -> List<B>): List<Either<L, B>> =
    map { it.toListK() }.traverse(ListApplicative) { a: ListKind<A> ->
      f(a.narrowList().list).toListK()
    }.narrowList().list

fun <L, A, B> Either<L, Option<A>>.traverseOption(f: (Option<A>) -> Option<B>): Option<Either<L, B>> =
    traverse(OptionApplicative) {
      f(it.narrowOption())
    }.narrowOption()

fun <L, A, G> Either<L, K1<G, A>>.sequence(app: Applicative<G>): K1<G, Either<L, A>> =
    EitherTraverse.instance<L>().sequence(this, app).narrowInnerEither()

fun <L, A> Either<L, ListK<A>>.sequenceList(): ListK<Either<L, A>> =
    sequence(ListApplicative).narrowList()

fun <L, A> Either<L, List<A>>.sequenceList(): List<Either<L, A>> =
    map { it.toListK() }.sequence(ListApplicative).narrowList().list

fun <L, A> Either<L, Option<A>>.sequenceOption(): Option<Either<L, A>> =
    sequence(OptionApplicative).narrowOption()
