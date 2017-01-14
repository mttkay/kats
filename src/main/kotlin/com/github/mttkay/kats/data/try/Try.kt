package com.github.mttkay.kats.data.`try`

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.data.`try`.Try.Failure
import com.github.mttkay.kats.data.`try`.Try.Success
import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.Either.Left
import com.github.mttkay.kats.data.either.Either.Right
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.Option.None
import com.github.mttkay.kats.data.option.Option.Some

typealias TryKind<A> = K1<Try.F, A>

fun <A> TryKind<A>.narrowTry(): Try<A> = this as Try<A>
@Suppress("UNCHECKED_CAST")
fun <A> Failure<*>.cast(): Try<A> = this as Try<A>

sealed class Try<out A> : TryKind<A> {

  class F

  companion object {

    fun <A> lift(f: () -> A): () -> Try<A> = { Try.run(f) }

    inline fun <A> run(f: () -> A): Try<A> =
        try {
          Success(f())
        } catch (e: Throwable) {
          //TODO: don't catch fatal errors
          Failure(e)
        }

    inline fun <A, B> run(a: A, f: (A) -> B): Try<B> =
        try {
          Success(f(a))
        } catch (e: Throwable) {
          //TODO: don't catch fatal errors
          Failure(e)
        }
  }

  abstract val isFailure: Boolean

  abstract val isSuccess: Boolean

  abstract val get: A

  data class Success<out A>(val value: A) : Try<A>() {

    override val isFailure = false

    override val isSuccess = true

    override val get: A = value
  }

  data class Failure<out A>(val exception: Throwable) : Try<A>() {

    override val isFailure = true

    override val isSuccess = false

    override val get: A
      get() = throw exception
  }
}

inline fun <A> Try<A>.getOrElse(default: () -> A) =
    if (isSuccess) get else default()

fun <A> Try<A>.toEither(): Either<Throwable, A> =
    when (this) {
      is Failure -> Left(exception)
      is Success -> Right(value)
    }

inline fun <L, R> Try<R>.toEither(mapError: (Throwable) -> L): Either<L, R> =
    when (this) {
      is Failure -> Left(mapError(exception))
      is Success -> Right(value)
    }

fun <A> Try<A>.toOption(): Option<A> = if (isSuccess) Some(get) else None
