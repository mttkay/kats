package com.github.mttkay.kats

interface Monoid<A> : Semigroup<A> {

  companion object {
    fun <A> of(empty: A, combine: (A, A) -> A) = object : Monoid<A> {
      override fun combine(a1: A, a2: A): A = combine(a1, a2)

      override fun combineAll(elems: Collection<A?>): A = combineAll(elems, empty, combine)

      override fun combineAll(vararg elems: A?): A = combineAll(elems.asList(), empty, combine)

      override fun maybeCombine(a1: A?, a2: A?): A? = Semigroup.maybeCombine(a1, a2, combine)

      override val empty: A = empty
    }

    inline fun <A, B : Collection<A>> ofCollection(empty: B, crossinline fromList: (List<A>) -> B): Monoid<B> =
        of(empty) { a, b -> fromList(a + b) }

    fun <A> ofList(): Monoid<List<A>> = ofCollection(emptyList()) { it }

    fun <A> ofSet(): Monoid<Set<A>> = ofCollection(emptySet()) { it.toSet() }

    inline fun <A> combineAll(elems: Collection<A?>, empty: A, combine: (A, A) -> A): A =
        Semigroup.combineAll(elems, combine) ?: empty
  }

  val empty: A

  override fun combineAll(elems: Collection<A?>): A

  override fun combineAll(vararg elems: A?): A
}
