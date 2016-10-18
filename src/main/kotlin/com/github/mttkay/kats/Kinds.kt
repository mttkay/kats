package com.github.mttkay.kats

interface K1<F, out A>
interface K2<F, A, B> : K1<K1<F, A>, B>
