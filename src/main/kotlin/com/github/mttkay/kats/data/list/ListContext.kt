package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.ext.collection.fold

typealias ListKind<A> = K1<ListContext.F, A>

fun <A> ListKind<A>.narrow(): ListContext<A> = this as ListContext<A>

class ListContext<out A>(val list: List<A>) : ListKind<A> {

  class F {}

  constructor(vararg values: A) : this(values.toList())

  infix fun <B> fmap(f: (A) -> B): ListContext<B> = ListFunctor.fmap(this, f)

  infix fun <B> flatMap(f: (A) -> ListContext<B>): ListContext<B> = ListMonad.flatMap(this, f)

  fun <B> foldLeft(b: B, f: (B, A) -> B): B = ListFoldable.foldLeft(this, b, f)

  fun <B> foldRight(b: B, f: (A, B) -> B): B = ListFoldable.foldRight(this, b, f)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as ListContext<*>

    if (list != other.list) return false

    return true
  }

  override fun hashCode(): Int {
    return list.hashCode()
  }

  override fun toString(): String = list.toString()
}

infix fun <A> ListContext<A>.fold(m: Monoid<A>): A = this.list.fold(m)
