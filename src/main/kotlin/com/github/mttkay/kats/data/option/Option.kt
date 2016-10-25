package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.K1

typealias OptionKind<A> = K1<Option.F, A>

sealed class Option<out A> : OptionKind<A> {

  class F

  companion object {
    fun <A> narrow(kind: OptionKind<A>): Option<A> = kind as Option<A>
  }

  class Some<out A>(val value: A) : Option<A>() {
    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other?.javaClass != javaClass) return false

      other as Some<*>

      if (value != other.value) return false

      return true
    }

    override fun hashCode(): Int {
      return value?.hashCode() ?: 0
    }
  }

  object None : Option<Nothing>()

  val orNull: A?
    get() = when (this) {
      is Some -> value
      is None -> null
    }

  fun <B> fmap(f: (A) -> B): Option<B> = OptionFunctor.fmap(this, f)
  fun <B> map(f: (A) -> B): B? = fmap(f).orNull
}

fun <A> Option<A>.getOrElse(other: () -> A): A = when (this) {
  is Option.Some -> value
  is Option.None -> other()
}
