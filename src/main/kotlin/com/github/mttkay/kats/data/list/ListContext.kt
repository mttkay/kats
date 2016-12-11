package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.ext.collection.fold

typealias ListKind<A> = K1<ListContext.F, A>

fun <A> ListKind<A>.narrowList(): ListContext<A> = this as ListContext<A>

@Suppress("UNCHECKED_CAST")
fun <A, B> ((ListKind<A>) -> ListKind<B>).narrowListFn(): (ListContext<A>) -> ListContext<B> =
    this as (ListContext<A>) -> ListContext<B>

@Suppress("UNCHECKED_CAST")
fun <A, G> K1<G, K1<ListContext.F, A>>.narrowInnerList(): K1<G, ListContext<A>> =
    this as K1<G, ListContext<A>>

class ListContext<out A>(val list: List<A>) : ListKind<A> {

  class F {}

  companion object {

    private val emptyInstance = ListContext<Any>()

    @Suppress("UNCHECKED_CAST")
    fun <A> empty(): ListContext<A> = emptyInstance as ListContext<A>
  }

  constructor(vararg values: A) : this(values.toList())

  constructor(head: A, tail: List<A>) : this(listOf(head) + tail)

  infix fun <B> map(f: (A) -> B): ListContext<B> = ListFunctor.map(this, f)

  infix fun <B> flatMap(f: (A) -> ListContext<B>): ListContext<B> = ListMonad.flatMap(this, f)

  fun <A> ListContext<A>.fold(m: Monoid<A>): A = this.list.fold(m)

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
