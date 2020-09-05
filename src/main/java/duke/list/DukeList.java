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

    boolean isValidIndex(int index);

    Storable remove(int index);
}
