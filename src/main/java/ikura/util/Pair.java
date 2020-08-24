// Pair.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.function.Function;
import java.util.function.BiFunction;

public class Pair<T, U> {

    private final T one;
    private final U two;

    public Pair(T a, U b) {
        this.one = a;
        this.two = b;
    }

    public T fst() {
        return this.one;
    }

    public U snd() {
        return this.two;
    }

    public <T1, U1> Pair<T1, U1> map(BiFunction<? super T, ? super U, Pair<T1, U1>> fn) {
        return fn.apply(this.one, this.two);
    }

    public <T1> Pair<T1, U> mapFst(Function<? super T, ? extends T1> fn) {
        return new Pair<T1, U>(fn.apply(this.one), this.two);
    }

    public <U1> Pair<T, U1> mapSnd(Function<? super U, ? extends U1> fn) {
        return new Pair<T, U1>(this.one, fn.apply(this.two));
    }
}
