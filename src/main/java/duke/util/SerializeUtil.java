package duke.util;

import java.io.*;

/**
 * A utility class designed to serialize/deserialize obects
 * that are serializable.
 */
public class SerializeUtil {

    /**
     * Serialize an object in to byte array.
     *
     * @param object Obect to be serialized
     * @return serialized object in the form of byte array
     */
    public static byte[] serialize(Object object) {

        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            System.err.println("Failed to serialize " + e.getMessage());
        }
        return bytes;
    }

    /**
     * Deserialize byte array into an object.
     *
     * @param bytes An byte array to be deserialized
     * @return Object of deserialized byte array
     * @throws IOException If stream fails to read
     * @throws ClassNotFoundException If class not found
     */
    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
        } catch (Exception e) {
            System.err.println("Failed to deserialize " + e.getMessage());
        }
        return ois.readObject();
    }
}