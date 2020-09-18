package duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * A class containing helper functions for reading and writing to disk.
 */
class StorageHelper {

    /**
     * Writes to the given object to a file, creating the file and any parent directories if necessary. If the
     * file already exists, it is overwritten.
     *
     * @param object Object to save to disk.
     * @param filepath Path to the file to write to.
     * @throws FileWritingException If file cannot be written due to an IOException or SecurityException.
     */
    static void save(Serializable object, String filepath) throws FileWritingException {
        File file = new File(filepath);

        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); // may throw SecurityException
            }
            // may throw IOException
            Files.writeString(file.toPath(), object.serialize(), StandardCharsets.UTF_8);
        } catch (IOException | SecurityException e) {
            throw new FileWritingException();
        }
    }

    /**
     * Reads the contents of a file as a String and returns the object represented by the stored String.
     *
     * @param deserializer Deserializer which deserializes the String in the file.
     * @param filepath Path to file to read from.
     * @param <T> Type of object returned.
     * @return Object that was stored in the file.
     * @throws FileMissingException If the file to read from is missing.
     * @throws FileReadingException If the file to read form is present, but cannot be read.
     * @throws DeserializingException If the file contents cannot be deserialized.
     */
    static <T> T open(Deserializer<T> deserializer, String filepath)
            throws FileMissingException, FileReadingException, DeserializingException {
        File file = new File(filepath);

        if (!file.exists()) {
            throw new FileMissingException();
        }

        try {
            String s = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            return deserializer.deserialize(s);
        } catch (IOException e) {
            throw new FileReadingException();
        }
    }
}
