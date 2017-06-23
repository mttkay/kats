package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monad
import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.EitherT

data class OptionT<F, A>(val value: K1<F, Option<A>>,
                         private val monad: Monad<F>) {

  companion object {

    fun <F, A> some(v: K1<F, A>, monad: Monad<F>): OptionT<F, A> =
        OptionT(monad.map(v) { Option.Some(it) }, monad)

    fun <F, A> none(monad: Monad<F>): OptionT<F, A> =
        OptionT(monad.pure(Option.None), monad)

  }

  val isEmpty: K1<F, Boolean> = monad.map(value) { it.isEmpty }

  val isDefined: K1<F, Boolean> = monad.map(value) { it.isDefined }

  fun <B> fold(default: () -> B, f: (A) -> B): K1<F, B> =
      monad.map(value) { it.fold(default(), f) }

  fun <B> cata(default: () -> B, f: (A) -> B): K1<F, B> =
      fold(default, f)

  fun <B> map(f: (A) -> B): OptionT<F, B> =
      OptionT(monad.map(value) { it.map(f) }, monad)

  fun <B> flatMap(f: (A) -> OptionT<F, B>): OptionT<F, B> =
      flatMapF { f(it).value }

  fun <B> flatMapF(f: (A) -> K1<F, Option<B>>): OptionT<F, B> =
      OptionT(monad.flatMap(value) {
        it.fold(monad.pure<Option<B>>(Option.None), f)
      }, monad)

  fun <B> transform(f: (Option<A>) -> Option<B>): OptionT<F, B> =
      OptionT(monad.map(value) { f(it) }, monad)

  fun getOrElse(default: () -> A): K1<F, A> =
      monad.map(value) { it.getOrElse(default) }

  fun getOrElseF(default: () -> K1<F, A>): K1<F, A> =
      monad.flatMap(value) { it.fold(default()) { monad.pure(it) } }

  fun orElse(default: () -> OptionT<F, A>): OptionT<F, A> =
      orElseF({ -> default.invoke().value })

  fun orElseF(default: () -> K1<F, Option<A>>): OptionT<F, A> =
    OptionT(
        monad.flatMap(value) {
          when (it) {
            is Option.None -> default()
            is Option.Some -> monad.pure(it)
          }
        }, monad)

  fun <B> subflatMap(f: (A) -> Option<B>): OptionT<F, B> =
      transform { it.flatMap(f) }

  fun <L> toRight(left: () -> L): EitherT<F, L, A> =
      EitherT(cata({ -> Either.Left<L, A>(left()) }) { Either.Right<L, A>(it) }, monad)

  fun <R> toLeft(right: () -> R): EitherT<F, A, R> =
      EitherT(cata({ -> Either.Right<A, R>(right()) }) { Either.Left<A, R>(it) }, monad)
}