package duke.parser;

import duke.exception.DukeParseException;
import duke.storage.Storable;

/**
 * Represents the interface of different storage parser.
 * @param <T> the object to be created after parsing.
 */
public interface StorageParser<T extends Storable> {

    /**
     * Parses the storage <code>String</code>.
     *
     * @param storageString the <code>String</code> to be passed.
     * @return the parsed object.
     * @throws DukeParseException if there are any errors in parsing.
     */
    T parseStorageString(String storageString) throws DukeParseException;
}
