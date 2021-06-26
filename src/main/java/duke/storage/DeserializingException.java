package duke.storage;

/**
 * This Exception is thrown by a Deserializer's deserialize function if the given String cannot be deserialized.
 * This exception may be propagated by StorageHelper's open function.
 */
class DeserializingException extends Exception {

}
