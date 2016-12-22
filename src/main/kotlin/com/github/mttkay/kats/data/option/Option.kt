package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.K1

typealias OptionKind<A> = K1<Option.F, A>

fun <A> OptionKind<A>.narrowOption(): Option<A> = this as Option<A>

@Suppress("UNCHECKED_CAST")
fun <A, F> K1<F, OptionKind<A>>.narrowInnerOption(): K1<F, Option<A>> = this as K1<F, Option<A>>

sealed class Option<out A> : OptionKind<A> {

  class F

  data class Some<out A>(val value: A) : Option<A>() {

    override val isEmpty: Boolean = false

    override val isDefined: Boolean = true
  }

  object None : Option<Nothing>() {

    override val isEmpty: Boolean = true

    override val isDefined: Boolean = false

    override fun toString(): String = "None"
  }

  abstract val isEmpty: Boolean

  abstract val isDefined: Boolean

  val orNull: A?
    get() = when (this) {
      is Some -> value
      is None -> null
    }

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
