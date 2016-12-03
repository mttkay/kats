package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1

typealias ListKind<A> = K1<ListContext.F, A>

@Suppress("UNCHECKED_CAST")
fun <A> ListKind<A>.narrow(): ListContext<A> =
    this as ListContext<A>

@Suppress("UNCHECKED_CAST")
fun <A, G> K1<G, K1<ListContext.F, A>>.narrowInner(): K1<G, ListContext<A>> =
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
