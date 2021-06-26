package duke.storage;

/**
 * A functional interface for deserializing a String back into an object.
 *
 * @param <T> Type of object returned by the deserialize function.
 */
interface Deserializer<T> {

    /**
     * Returns the object represented by the given String if possible, otherwise throws a DeserializingException.
     *
     * @param string String to parse and create object from.
     * @return Object represented by the given String.
     * @throws DeserializingException If the String cannot be parsed to return this type of object.
     */
    T deserialize(String string) throws DeserializingException;
}
