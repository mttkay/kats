package com.github.mttkay.kats

import org.assertj.core.api.Assertions

infix fun Any.mustEqual(that: Any) {
  Assertions.assertThat(this).isEqualTo(that)
}
