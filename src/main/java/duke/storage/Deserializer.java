package duke.storage;

public interface Deserializer<T> {
   public T deserialize(String string) throws DeserializingException;
}
