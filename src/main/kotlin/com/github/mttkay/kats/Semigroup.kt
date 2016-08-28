package com.github.mttkay.kats

interface Semigroup<A> {

  companion object {
    fun <A> create(combine: (A, A) -> A) = object : Semigroup<A> {
      override fun combine(a1: A, a2: A): A = combine(a1, a2)

      override fun combineAll(elems: Collection<A?>) = combineAll(elems, combine)

      override fun combineAll(vararg elems: A?) = combineAll(elems.asList(), combine)

      override fun maybeCombine(a1: A?, a2: A?): A? = maybeCombine(a1, a2, combine)
    }

    inline fun <A> maybeCombine(a1: A?, a2: A?, combine: (A, A) -> A): A? = when {
      a1 != null -> a2?.let { combine(a1, a2) } ?: a1
      a2 != null -> a1?.let { combine(a1, a2) } ?: a2
      else -> null
    }

    inline fun <A> combineAll(elems: Collection<A?>, combine: (A, A) -> A): A? =
        if (elems.isEmpty()) null
        else elems.reduce { a, b -> maybeCombine(a, b, combine) }
  }

  fun combine(a1: A, a2: A): A

  fun combineAll(elems: Collection<A?>): A?

  fun combineAll(vararg elems: A?): A?

  fun maybeCombine(a1: A?, a2: A?): A?

}
