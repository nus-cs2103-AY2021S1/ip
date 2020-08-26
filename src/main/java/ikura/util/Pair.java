// Pair.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.function.Function;
import java.util.function.BiFunction;

/**
 * A container class with two values, of types L and R. No restrictions are
 * imposed on the types or their values.
 */
public class Pair<T, U> {

    private final T one;
    private final U two;

    /**
     * Constructs a new Pair with the specified values.
     *
     * @param a the first value.
     * @param b the second value.
     */
    public Pair(T a, U b) {
        this.one = a;
        this.two = b;
    }

    /**
     * Obtains the first value from the pair.
     *
     * @return the first value.
     */
    public T fst() {
        return this.one;
    }

    /**
     * Obtains the second value from the pair.
     *
     * @return the second value.
     */
    public U snd() {
        return this.two;
    }

    /**
     * Performs a map on both values of the pair, returning the new pair;
     * the original pair is left unmodified. The BiFunction should return
     * the new pair.
     *
     * @param fn the function to map the values on.
     * @return   the new Pair.
     */
    public <T1, U1> Pair<T1, U1> map(BiFunction<? super T, ? super U, Pair<T1, U1>> fn) {
        return fn.apply(this.one, this.two);
    }

    /**
     * Performs a map on only the first value of the pair, returning the
     * new pair; the original pair is left unmodified. The second value of
     * the pair is returned as-is.
     *
     * @param fn the function to map the first value on.
     * @return   the new Pair.
     */
    public <T1> Pair<T1, U> mapFst(Function<? super T, ? extends T1> fn) {
        return new Pair<T1, U>(fn.apply(this.one), this.two);
    }

    /**
     * Performs a map on only the second value of the pair, returning the
     * new pair; the original pair is left unmodified. The first value of
     * the pair is returned as-is.
     *
     * @param fn the function to map the second value on.
     * @return   the new Pair.
     */
    public <U1> Pair<T, U1> mapSnd(Function<? super U, ? extends U1> fn) {
        return new Pair<T, U1>(this.one, fn.apply(this.two));
    }
}
