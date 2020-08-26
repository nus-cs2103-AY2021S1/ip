// Either.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.Optional;
import java.util.function.Function;


/**
 * A container class that wraps a value of either type L (the 'left' value), or
 * of type R (the 'right' value), but not both at once -- an Either contains only
 * either a left or a right value.
 */
public class Either<L, R> {

    private final L leftValue;
    private final R rightValue;

    private Either(L leftVal, R rightVal) {
        this.leftValue = leftVal;
        this.rightValue = rightVal;

        assert this.leftValue == null || this.rightValue == null;
    }

    /**
     *  Returns true if the Either contains a left value, false otherwise.
     *
     *  @return true if this is a left value, false otherwise.
    */
    public boolean isLeft() {
        return this.leftValue != null;
    }

    /**
     * Returns true if the Either contains a right value, false otherwise.
     *
     * @return True if this is a right value, false otherwise.
     */
    public boolean isRight() {
        return this.rightValue != null;
    }

    /**
     * Obtains the stored left value in the Either. If the Either contains a
     * right value, throws a NoSuchElementException.
     *
     *  @return The left value contained in the Either.
     */
    public L fromLeft() {
        return Optional.ofNullable(this.leftValue).get();
    }

    /**
     * Obtains the stored right value in the Either. If the Either contains a
     * right value, throws a NoSuchElementException.
     *
     *  @return The right value contained in the Either.
     */
    public R fromRight() {
        return Optional.ofNullable(this.rightValue).get();
    }

    public <L1> Either<L1, R> mapLeft(Function<? super L, ? extends L1> fn) {
        return this.isLeft()
            ? Either.left(fn.apply(this.leftValue))
            : Either.right(this.rightValue);
    }

    public <R1> Either<L, R1> mapRight(Function<? super R, ? extends R1> fn) {
        return this.isRight()
            ? Either.right(fn.apply(this.rightValue))
            : Either.left(this.leftValue);
    }

    /**
     * Creates an Either containing a left value.
     *
     *  @return An Either containing a left value.
     */
    public static <L, R> Either<L, R> left(L l) {
        return new Either<L, R>(l, null);
    }

    /**
     * Creates an Either containing a right value.
     *
     *  @return An Either containing a right value.
     */
    public static <L, R> Either<L, R> right(R r) {
        return new Either<L, R>(null, r);
    }
}
