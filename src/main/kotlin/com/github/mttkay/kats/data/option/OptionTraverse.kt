package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.*
import com.github.mttkay.kats.data.list.ListApplicative
import com.github.mttkay.kats.data.list.ListContext
import com.github.mttkay.kats.data.list.ListKind
import com.github.mttkay.kats.data.list.narrowList
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some
import com.github.mttkay.kats.ext.collection.liftList

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

// TODO: there's an awful lot of back and forth here; look into simplifying this
fun <A, B> Option<List<A>>.traverseList(f: (List<A>) -> List<B>): List<Option<B>> =
    fmap { it.liftList() }.traverse(ListApplicative) { a: ListKind<A> ->
      f(a.narrowList().list).liftList()
    }.narrowList().list

fun <A, G> Option<K1<G, A>>.sequence(app: Applicative<G>): K1<G, Option<A>> =
    OptionTraverse.sequence(this, app).narrowInnerOption()

fun <A> Option<ListContext<A>>.sequenceList(): ListContext<Option<A>> =
    sequence(ListApplicative).narrowList()

fun <A> Option<List<A>>.sequenceList(): List<Option<A>> =
    fmap { it.liftList() }.sequence(ListApplicative).narrowList().list
