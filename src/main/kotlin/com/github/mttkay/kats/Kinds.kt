package com.github.mttkay.kats

interface K1<out F, out A>
interface K2<out F, out A, out B> : K1<K1<F, A>, B>
