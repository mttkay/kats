package com.github.mttkay.kats

interface Semigroup<A> {

  companion object {
    fun <A> create(combine: (A, A) -> A) = object : Semigroup<A> {
      override fun combine(a1: A, a2: A): A = combine(a1, a2)
    }
  }

  fun combine(a1: A, a2: A): A

  fun combineAll(vararg elems: A?): A? = combineAll(elems.asList())

  fun combineAll(elems: Collection<A?>): A? =
      if (elems.isEmpty()) null
      else elems.reduce { a, b -> maybeCombine(a, b) }

  fun maybeCombine(a1: A?, a2: A?): A? = when {
    a1 != null -> a2?.let { combine(a1, a2) } ?: a1
    a2 != null -> a1?.let { combine(a1, a2) } ?: a2
    else -> null
  }
}
