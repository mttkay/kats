package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.Monoid
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some

class OptionMonoid<A>(private val monoidA: Monoid<A>) : Monoid<Option<A>> {

  override val empty: Some<A> = Some(monoidA.empty)

  override fun combine(a1: Option<A>, a2: Option<A>): Option<A> = when (a1) {
    is None -> a1
    is Some -> when (a2) {
      is None -> a2
      is Some -> Some(monoidA.combine(a1.value, a2.value))
    }
  }
}
