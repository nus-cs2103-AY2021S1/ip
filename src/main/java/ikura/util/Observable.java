// Observable.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.function.Consumer;

public interface Observable<T> {

    /**
     * Add an observer that listens for changes on this object.
     *
     * @param observer the function to run when changes are made
     */
    public void addObserver(Consumer<T> observer);
}
