package com.github.mttkay.kats

interface Foldable<in F> {

  fun <A, B> foldLeft(fa: K1<F, A>, b: B, f: (B, A) -> B): B

  fun <A, B> foldRight(fa: K1<F, A>, b: B, f: (A, B) -> B): B

}
