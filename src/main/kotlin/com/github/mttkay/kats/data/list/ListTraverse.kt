package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.*
import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.EitherApplicative
import com.github.mttkay.kats.data.either.narrowEither
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrowOption

object ListTraverse :
    Traverse<ListK.F>,
    Foldable<ListK.F> by ListFoldable,
    Functor<ListK.F> by ListFunctor {

  override fun <G, A, B> traverse(fa: ListKind<A>, app: Applicative<G>, f: (A) -> K1<G, B>): K1<G, ListK<B>> {
    return foldRight(fa, app.pure(ListK.empty())) { a, list ->
      app.map2(f(a), list) { (b, y) -> ListK(b, y.list) }
    }
  }

}

fun <A, B, G> ListK<K1<G, A>>.traverse(app: Applicative<G>, f: (K1<G, A>) -> K1<G, B>): K1<G, ListK<B>> =
    ListTraverse.traverse(this, app, f)

fun <A, B> ListK<Option<A>>.traverseOption(f: (Option<A>) -> Option<B>): Option<ListK<B>> =
    traverse(OptionApplicative) {
      f(it.narrowOption())
    }.narrowOption()

fun <A, G> ListK<K1<G, A>>.sequence(app: Applicative<G>): K1<G, ListK<A>> =
    ListTraverse.sequence(this, app).narrowInnerList()

fun <A> ListK<Option<A>>.sequenceOption(): Option<ListK<A>> =
    sequence(OptionApplicative).narrowOption()

fun <L, R> ListK<Either<L, R>>.sequenceEither(): Either<L, ListK<R>> =
    sequence(EitherApplicative.instance<L>()).narrowEither()
