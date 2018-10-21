// Copyright © 2012-2018 Vaughn Vernon. All rights reserved.
//
// This Source Code Form is subject to the terms of the
// Mozilla Public License, v. 2.0. If a copy of the MPL
// was not distributed with this file, You can obtain
// one at https://mozilla.org/MPL/2.0/.

package io.vlingo.common;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Outcome<FailureT extends Throwable, SuccessT> {
    <NextSuccessT>
    Outcome<FailureT, NextSuccessT> andThen(final Function<SuccessT, NextSuccessT> action);

    <NextFailureT extends Throwable, NextSuccessT>
    Outcome<NextFailureT, NextSuccessT> andThenInto(final Function<SuccessT, Outcome<NextFailureT, NextSuccessT>> action);

    void atLeastConsume(final Consumer<SuccessT> consumer);

    Outcome<FailureT, SuccessT> otherwise(final Function<FailureT, SuccessT> action);

    <NextFailureT extends Throwable, NextSuccessT>
    Outcome<NextFailureT, NextSuccessT> otherwiseInto(final Function<FailureT, Outcome<NextFailureT, NextSuccessT>> action);

    SuccessT get() throws FailureT;

    SuccessT getOrNull();

    <NextSuccessT>
    NextSuccessT resolve(
            final Function<FailureT, NextSuccessT> onFailedOutcome,
            final Function<SuccessT, NextSuccessT> onSuccessfulOutcome
    );

    Optional<SuccessT>
    asOptional();

    Completes<SuccessT>
    asCompletes();
}
