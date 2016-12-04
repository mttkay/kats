package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.data.`try`.Try.Failure

typealias TryKind<A> = K1<Try.F, A>

fun <A> TryKind<A>.narrowTry(): Try<A> = this as Try<A>
@Suppress("UNCHECKED_CAST")
fun <A> Failure<*>.cast(): Try<A> = this as Try<A>

sealed class Try<out A> : TryKind<A> {

  class F

  companion object {
    inline fun <A, B> run(a: A, f: (A) -> B): Try<B> =
        try {
          Success(f(a))
        } catch (e: Throwable) {
          Failure(e)
        }
  }

  abstract val isFailure: Boolean

  abstract val isSuccess: Boolean

  abstract val get: A

  infix fun <B> map(f: (A) -> B): Try<B> = TryFunctor.map(this, f)

  infix fun <B> flatMap(f: (A) -> Try<B>): Try<B> = TryMonad.flatMap(this, f)

  class Success<out A>(val value: A) : Try<A>() {

    override val isFailure = false

    override val isSuccess = true

    override val get: A = value

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other?.javaClass != javaClass) return false

      other as Success<*>

      if (value != other.value) return false

      return true
    }

    override fun hashCode(): Int {
      return value?.hashCode() ?: 0
    }

    override fun toString(): String {
      return "Success(value=$value)"
    }
  }

  class Failure<out A>(val exception: Throwable) : Try<A>() {

    override val isFailure = true

    override val isSuccess = false

    override val get: A
      get() = throw exception

    override fun equals(other: Any?): Boolean {
      if (this === other) return true
      if (other?.javaClass != javaClass) return false

      other as Failure<*>

      if (exception != other.exception) return false

      return true
    }

    override fun hashCode(): Int {
      return exception.hashCode()
    }

    override fun toString(): String {
      return "Failure(exception=$exception)"
    }
  }
}

inline fun <A> Try<A>.getOrElse(default: () -> A) =
    when (this) {
      is Try.Success -> this.value
      is Failure -> default()
    }
