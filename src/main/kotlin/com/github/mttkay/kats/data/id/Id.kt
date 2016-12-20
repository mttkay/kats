package com.github.mttkay.kats.data.id

import com.github.mttkay.kats.K1

typealias IdKind<A> = K1<Id.F, A>

fun <A> IdKind<A>.narrowId(): Id<A> = this as Id<A>

data class Id<out A>(val value: A) : IdKind<A> {

  class F

}
