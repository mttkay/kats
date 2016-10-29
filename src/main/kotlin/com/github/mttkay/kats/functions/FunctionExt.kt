package com.github.mttkay.kats.functions

infix fun <A, B, C> ((B) -> C).compose(f: (A) -> B): (A) -> C = { a: A ->
  this(f(a))
}

infix fun <A, B, C> ((A) -> B).andThen(g: (B) -> C): (A) -> C = { a: A ->
  g(this(a))
}
