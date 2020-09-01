// StreamUtils.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.stream.Stream;

public class StreamUtils {

    /**
     * Creates a stream by zipping two streams together. The length of the resulting
     * stream is the minimum of either of the inputs.
     *
     * @param s1 the first stream to zip
     * @param s2 the second stream to zip
     * @return a Stream of Pairs, comprising the elements from each stream.
     */
    public static <T, U> Stream<Pair<T, U>> zip(Stream<T> s1, Stream<U> s2) {

        var i2 = s2.iterator();
        return s1.map(x1 -> i2.hasNext() ? new Pair<>(x1, i2.next()) : null)
            .takeWhile(x -> x != null);
    }

    /**
     * Creates a stream by zipping the input stream together with a stream of indices,
     * beginning at 0. This is implemented in terms of zip(). The index is the first
     * item in the pair.
     *
     * @param s the stream to use
     * @return a Stream of Pairs, with the first item being the 0-based index
     */
    public static <T> Stream<Pair<Integer, T>> indexed(Stream<T> s) {
        return StreamUtils.zip(Stream.iterate(0, i -> i + 1), s);
    }
}
