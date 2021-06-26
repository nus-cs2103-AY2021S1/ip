package duke.storage;

/**
 * A functional interface representing an object which can be serialized to a String. Used by StorageHelper's save
 * function.
 */
interface Serializable {

    /**
     * Returns the serialized String representation of this object. The serialized representation should contain
     * enough information to recreate another identical instance of the object.
     *
     * @return Serialized String representation of this object.
     */
    String serialize();
}
