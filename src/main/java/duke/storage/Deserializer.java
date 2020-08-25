package duke.storage;

interface Deserializer<T> {
   T deserialize(String string) throws DeserializingException;
}
