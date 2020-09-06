package duke.list;

import duke.storage.Storable;

import java.util.ArrayList;
import java.util.Iterator;

/** Represents the interface of lists in Duke. */
public abstract class StorableList<T extends Storable> implements Iterable<T> {
    protected ArrayList<T> list;

    /**
     * Constructor method.
     *
     * @param list the <code>StorableList</code> as provided from the subclasses.
     */
    StorableList(ArrayList<T> list) {
        this.list = list;
    }

    /**
     * Implements the <code>Iterable</code> interface for <code>StorableList</code> to be iterable.
     *
     * @return the iterator form of <code>StorableList</code>.
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    /**
     * Gets the size of the list.
     *
     * @return the size of the list.
     */
    public int getCurrCapacity() {
        return list.size();
    }

    /**
     * Adds the given <code>Storable</code> into <code>StorableList</code>.
     *
     * @param storable the <code>Storable</code> that is to be added.
     * @return the <code>Storable</code> that has been added.
     */
    public T add(T storable) {
        list.add(storable);
        return storable;
    }

    /**
     * Checks if the given index is within the capacity of the list.
     *
     * @param index the index to be checked on.
     * @return <code>true</code> if the index is valid.
     */
    public boolean isValidIndex(int index) {
        return index <= list.size() && index > 0;
    }

    /**
     * Removes the <code>Storable</code> at the index.
     *
     * @param index the index to be removed.
     * @return the removed <code>Storable</code>
     */
    public T remove(int index) {
        return list.remove(index - 1);
    }

    /**
     * Searches the description of the <code>Storable</code> with the given search word.
     *
     * @param searchWord the <code>String</code> to be searched for.
     * @return a <code>String</code> containing all searched <code>Storables</code>.
     */
    public abstract String search(String searchWord);
}
