package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Foldable
import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.ext.collection.fold


object ListFoldable : Foldable<ListContext.F> {

  override fun <A, B> foldLeft(fa: ListKind<A>, b: B, f: (B, A) -> B): B =
      fa.narrow().list.fold(b, f)

  override fun <A, B> foldRight(fa: ListKind<A>, b: B, f: (A, B) -> B): B =
      fa.narrow().list.foldRight(b, f)
}

infix fun <A> ListContext<A>.fold(m: Monoid<A>): A = this.list.fold(m)
