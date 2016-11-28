package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.Foldable


object ListFoldable : Foldable<ListContext.F> {

  override fun <A, B> foldLeft(fa: ListKind<A>, b: B, f: (B, A) -> B): B =
      fa.narrow().list.fold(b, f)

  override fun <A, B> foldRight(fa: ListKind<A>, b: B, f: (A, B) -> B): B =
      fa.narrow().list.foldRight(b, f)
}
