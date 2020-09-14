// Observable.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

import java.util.List;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * A base class for all Observable objects, that can hold a list of observers, and can
 * update said observers via a callback when the object changes.
 *
 * Note that, if your observer callback modifies the object it is watching (such that
 * the object again needs to update its observers), the callback will not be called
 * a subsequent time.
 *
 * Because java is a great language, this cannot be an interface.
 */
public abstract class Observable<T> {

    // the list of observers.
    private final List<Consumer<T>> observers = new ArrayList<>();

    // the reentrancy counter, so we don't end up in a recursive spiral when a
    // callback ends up modifying the object that it is watching itself.
    private int depthCounter = 0;

    /**
     * Add an observer that listens for changes on this object.
     *
     * @param observer the function to run when changes are made
     */
    public void addObserver(Consumer<T> observer) {
        this.observers.add(observer);
    }

    /**
     * Helper method for Observable classes; this handles checking for the reentrancy of
     * observation methods.
     */
    @SuppressWarnings("unchecked")
    protected void updateObservers() {
        if (this.depthCounter == 0) {

            this.depthCounter += 1;
            for (var obs : observers) {

                obs.accept((T) this);
            }

            this.depthCounter -= 1;
        }
    }
}
