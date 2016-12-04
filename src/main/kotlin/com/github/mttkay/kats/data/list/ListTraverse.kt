package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.*
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrowOption
import com.github.mttkay.kats.ext.collection.liftList

object ListTraverse :
    Traverse<ListContext.F>,
    Foldable<ListContext.F> by ListFoldable,
    Functor<ListContext.F> by ListFunctor {

  override fun <G, A, B> traverse(fa: ListKind<A>, app: Applicative<G>, f: (A) -> K1<G, B>): K1<G, ListContext<B>> {
    return foldRight(fa, app.pure(ListContext.empty())) { a, list ->
      app.map2(f(a), list) { (b, y) -> ListContext(b, y.list) }
    }
  }

}

fun <A, B, G> ListContext<K1<G, A>>.traverse(app: Applicative<G>, f: (K1<G, A>) -> K1<G, B>): K1<G, ListContext<B>> =
    ListTraverse.traverse(this, app, f)

fun <A, B> ListContext<Option<A>>.traverseOption(f: (Option<A>) -> Option<B>): Option<ListContext<B>> =
    traverse(OptionApplicative) {
      f(it.narrowOption())
    }.narrowOption()

fun <A, B> List<Option<A>>.traverseOption(f: (Option<A>) -> Option<B>): Option<List<B>> =
    liftList().traverseOption(f).fmap { it.list }

fun <A, G> ListContext<K1<G, A>>.sequence(app: Applicative<G>): K1<G, ListContext<A>> =
    ListTraverse.sequence(this, app).narrowInnerList()

fun <A> ListContext<Option<A>>.sequenceOption(): Option<ListContext<A>> =
    sequence(OptionApplicative).narrowOption()

fun <A> List<Option<A>>.sequenceOption(): Option<List<A>> =
    liftList().sequence(OptionApplicative).narrowOption().fmap { it.list }
