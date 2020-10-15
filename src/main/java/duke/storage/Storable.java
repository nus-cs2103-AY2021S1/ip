package duke.storage;

/**
 * Represents objects that can be stored in Duke <code>Storage</code>.
 */
public interface Storable {
    String DELIMITER = ";";

    /**
     * Converts the <code>Storable</code> into a storage <code>String</code>.
     *
     * @return the storage <code>String</code>.
     */
    String convertToStorageString();
}
