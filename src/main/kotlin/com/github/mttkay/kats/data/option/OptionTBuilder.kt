package com.github.mttkay.kats.data.option

import com.github.mttkay.kats.K1
import com.github.mttkay.kats.Monad

class OptionTBuilder<F, A>(private val monad: Monad<F>) {
  fun some(value: K1<F, A>): OptionT<F, A> = OptionT.some(value, monad)

  fun none(): OptionT<F, A> = OptionT.none(monad)
}