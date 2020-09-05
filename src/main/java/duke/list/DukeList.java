package duke.list;

import duke.storage.Storable;

/** Represents the interface of lists in Duke. */
public interface DukeList {

    /**
     * Gets the size of the list.
     *
     * @return the size of the list.
     */
    int getCurrCapacity();

    /**
     * Checks if the given index is within the capacity of the list.
     *
     * @param index the index to be checked on.
     * @return <code>true</code> if the index is valid.
     */
    boolean isValidIndex(int index);

    /**
     * Removes the <code>Storable</code> at the index.
     *
     * @param index the index to be removed.
     * @return the removed <code>Storable</code>
     */
    Storable remove(int index);

    /**
     * Searches the description of the <code>Storable</code> with the given search word.
     *
     * @param searchWord the <code>String</code> to be searched for.
     * @return a <code>String</code> containing all searched <code>Storables</code>.
     */
    String search(String searchWord);
}
