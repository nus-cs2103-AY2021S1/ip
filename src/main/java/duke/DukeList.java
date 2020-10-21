package duke;

import duke.command.UserInputException;

import java.util.ArrayList;

/**
 * A base class of lists that Duke is designed to support.
 * @param <T> The type of entries that can be stored in the list.
 */
public class DukeList<T> {
    /** The list of entries contained by the Duke-list. */
    protected final ArrayList<T> entries = new ArrayList<T>();

    /**
     * Constructs a new Duke-list object.
     */
    protected DukeList() {}

    /**
     * Gets the number of entries in the Duke-list.
     *
     * @return The number of entries currently in the Duke-list.
     */
    public int getNumEntries() {
        return entries.size();
    }

    /** Returns the entries in the Duke-list as an array of string objects. */
    public String[] getEntries() {
        int numEntries = entries.size();
        String[] entryStrings = new String[numEntries];
        for (int i = 0; i < numEntries; i++) {
            entryStrings[i] = entries.get(i).toString();
        }
        return entryStrings;
    }

    /**
     * Adds an entry to the current Duke-list.
     *
     * @param entry The entry to be added to the Duke-list.
     */
    public void addEntry(T entry) throws UserInputException {
        if (entries.contains(entry)) {
            throw UserInputException.DUPLICATE_ENTRY;
        }
        entries.add(entry);
    }


}
