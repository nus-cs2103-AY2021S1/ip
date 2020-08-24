// StreamUtils.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.stream.Stream;

public class StreamUtils {


    public static <T, U> Stream<Pair<T, U>>
        zip(Stream<T> s1, Stream<U> s2) {

        var i2 = s2.iterator();
        return s1.map(x1 -> i2.hasNext() ? new Pair<>(x1, i2.next()) : null)
            .takeWhile(x -> x != null);
    }

    public static <T> Stream<Pair<Integer, T>> indexed(Stream<T> s) {
        return StreamUtils.zip(Stream.iterate(0, i -> i + 1), s);
    }
}
