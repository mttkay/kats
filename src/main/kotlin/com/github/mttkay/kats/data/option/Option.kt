package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.K1

typealias OptionKind<A> = K1<Option.F, A>

fun <A> OptionKind<A>.narrow(): Option<A> = this as Option<A>

sealed class Option<out A> : OptionKind<A> {

  class F

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

    override fun toString(): String = "Some($value)"
  }

  object None : Option<Nothing>() {
    override fun toString(): String = "None"
  }

  val orNull: A?
    get() = when (this) {
      is Some -> value
      is None -> null
    }

  fun <B> fmap(f: (A) -> B): Option<B> = OptionFunctor.fmap(this, f)
  fun <B> map(f: (A) -> B): B? = fmap(f).orNull

  inline fun <B> fold(ifEmpty: B, f: (A) -> B): B = when (this) {
    is Option.Some -> f(value)
    is Option.None -> ifEmpty
  }
}

inline fun <A> Option<A>.getOrElse(other: () -> A): A = when (this) {
  is Option.Some -> value
  is Option.None -> other()
}

inline fun <A> Option<A>.orElse(other: () -> Option<A>): Option<A> = when (this) {
  is Option.Some -> this
  is Option.None -> other()
}
