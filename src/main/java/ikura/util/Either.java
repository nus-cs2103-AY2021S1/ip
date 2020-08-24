// Either.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.Optional;

public class Either<L, R> {

    private final L leftValue;
    private final R rightValue;

    private Either(L leftVal, R rightVal) {
        this.leftValue = leftVal;
        this.rightValue = rightVal;

        assert this.leftValue == null || this.rightValue == null;
    }

    public boolean isLeft() {
        return this.leftValue != null;
    }

    public boolean isRight() {
        return this.rightValue != null;
    }

    public L fromLeft() {
        return Optional.ofNullable(this.leftValue).get();
    }

    public R fromRight() {
        return Optional.ofNullable(this.rightValue).get();
    }

    public static <L, R> Either<L, R> left(L l) {
        return new Either<L, R>(l, null);
    }

    public static <L, R> Either<L, R> right(R r) {
        return new Either<L, R>(null, r);
    }
}
