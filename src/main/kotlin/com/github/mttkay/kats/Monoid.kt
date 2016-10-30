package com.github.mttkay.kats

interface Monoid<A> : Semigroup<A> {

  companion object {
    fun <A> create(empty: A, combine: (A, A) -> A) = object : Monoid<A> {
      override fun combine(a1: A, a2: A): A = combine(a1, a2)

      override val empty: A = empty
    }
  }

  val empty: A

  override fun combineAll(elems: Collection<A?>): A = super.combineAll(elems) ?: empty
}
