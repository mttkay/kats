package com.github.mttkay.kats.data.list

import com.github.mttkay.kats.K1

typealias ListKind<A> = K1<ListK.F, A>

fun <A> ListKind<A>.narrowList(): ListK<A> = this as ListK<A>

@Suppress("UNCHECKED_CAST")
fun <A, B> ((ListKind<A>) -> ListKind<B>).narrowListFn(): (ListK<A>) -> ListK<B> =
    this as (ListK<A>) -> ListK<B>

@Suppress("UNCHECKED_CAST")
fun <A, G> K1<G, K1<ListK.F, A>>.narrowInnerList(): K1<G, ListK<A>> =
    this as K1<G, ListK<A>>

/**
 * Wrapper class to give Kotlin [List]s the higher-kinded type treatment.
 */
class ListK<out A>(val list: List<A>) : ListKind<A> {

  class F

  companion object {

    private val emptyInstance = ListK<Any>()

    @Suppress("UNCHECKED_CAST")
    fun <A> empty(): ListK<A> = emptyInstance as ListK<A>
  }

  constructor(vararg values: A) : this(values.toList())

  constructor(head: A, tail: List<A>) : this(listOf(head) + tail)

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other?.javaClass != javaClass) return false

    other as ListK<*>

    if (list != other.list) return false

    return true
  }

  override fun hashCode(): Int {
    return list.hashCode()
  }

  override fun toString(): String = list.toString()
}
