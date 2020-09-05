package duke.storage;

/**
 * Represents objects that can be stored in Duke.
 */
public interface Storable {
    public static final String DELIMITER = ";";

    /**
     * Converts the <code>Storable</code> into a storage <code>String</code>.
     *
     * @return the storage <code>String</code>.
     */
    public String convertToStorageString();
}
