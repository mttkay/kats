package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.*
import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.list.narrowList
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some

object OptionTraverse :
    Traverse<Option.F>,
    Foldable<Option.F> by OptionFoldable,
    Functor<Option.F> by OptionFunctor {

  override fun <G, A, B> traverse(fa: OptionKind<A>, app: Applicative<G>, f: (A) -> K1<G, B>): K1<G, Option<B>> {
    val option = fa.narrowOption()
    return when (option) {
      is None -> app.pure(None)
      is Some -> app.fmap(f(option.value)) { Some(it) }
    }
  }
}

fun <A, B, G> Option<K1<G, A>>.traverse(app: Applicative<G>, f: (K1<G, A>) -> K1<G, B>): K1<G, Option<B>> =
    OptionTraverse.traverse(this, app, f)

fun <A, B> Option<ListContext<A>>.traverseList(f: (ListContext<A>) -> ListContext<B>): ListContext<Option<B>> =
    traverse(ListApplicative) {
      f(it.narrowList())
    }.narrowList()

fun <A, G> Option<K1<G, A>>.sequence(app: Applicative<G>): K1<G, Option<A>> =
    OptionTraverse.sequence(this, app).narrowInnerOption()

fun <A> Option<ListContext<A>>.sequenceList(): ListContext<Option<A>> =
    sequence(ListApplicative).narrowList()
