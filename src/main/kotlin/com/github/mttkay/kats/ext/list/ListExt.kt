package com.github.mttkay.kats.ext.list

import com.github.mttkay.kats.Applicative
import com.github.mttkay.kats.K1
import com.github.mttkay.kats.data.either.Either
import com.github.mttkay.kats.data.either.EitherApplicative
import com.github.mttkay.kats.data.either.narrowEither
import com.github.mttkay.kats.data.list.*
import com.github.mttkay.kats.data.option.Option
import com.github.mttkay.kats.data.option.OptionApplicative
import com.github.mttkay.kats.data.option.narrowOption
import com.github.mttkay.kats.ext.collection.liftList

// TRAVERSE

fun <A, B, G> List<K1<G, A>>.traverse(app: Applicative<G>, f: (K1<G, A>) -> K1<G, B>): K1<G, List<B>> =
    app.map(liftList().traverse(app, f), ListContext<B>::list)

fun <A, B> List<Option<A>>.traverseOption(f: (Option<A>) -> Option<B>): Option<List<B>> =
    liftList().traverseOption(f).map { it.list }

fun <A, G> List<K1<G, A>>.sequence(app: Applicative<G>): K1<G, List<A>> =
    app.map(liftList().sequence(app)) { it.list }

fun <A> List<Option<A>>.sequenceOption(): Option<List<A>> =
    sequence(OptionApplicative).narrowOption()

fun <L, R> List<Either<L, R>>.sequenceEither(): Either<L, List<R>> =
    sequence(EitherApplicative.instance<L>()).narrowEither()

// APPLICATIVE

infix fun <A, B> List<A>.product(that: List<B>): List<Pair<A, B>> =
    ListMonad.product(this.liftList(), that.liftList()).narrowList().list

infix operator fun <A, B> List<A>.times(that: List<B>): List<Pair<A, B>> = product(that)
