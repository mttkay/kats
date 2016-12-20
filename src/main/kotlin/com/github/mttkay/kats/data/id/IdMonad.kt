package com.github.mttkay.kats.data.id

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.Monad

object IdMonad : Monad<Id.F>, Applicative<Id.F> by IdApplicative {
  override fun <A, B> flatMap(fa: IdKind<A>, f: (A) -> IdKind<B>): Id<B> {
    val a = fa.narrowId().value
    return f(a).narrowId()
  }
}

fun <A, B> Id<A>.flatMap(f: (A) -> Id<B>): Id<B> = IdMonad.flatMap(this, f)
