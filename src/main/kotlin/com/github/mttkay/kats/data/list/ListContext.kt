package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1

typealias ListKind<A> = K1<ListContext.F, A>

fun <A> ListKind<A>.narrow(): ListContext<A> = this as ListContext<A>

class ListContext<out A>(val list: List<A>) : ListKind<A> {

  class F {}

  constructor(vararg values: A) : this(values.toList())

  fun <B> fmap(f: (A) -> B): ListContext<B> = ListFunctor.fmap(this, f)

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
