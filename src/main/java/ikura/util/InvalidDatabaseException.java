// InvalidDatabaseException.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.util;

/**
 * An exception that is thrown when there are errors when reading the database
 * (eg. missing entries)
 */
public class InvalidDatabaseException extends Exception {

    /**
     * Constructs a new InvalidDatabaseException with the given message.
     *
     * @param msg the exception message.
     */
    public InvalidDatabaseException(String msg) {
        super(msg);
    }
}
