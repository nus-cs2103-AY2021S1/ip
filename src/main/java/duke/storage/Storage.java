package duke.storage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Storage {
    public static void save(Serializable object, String filepath) throws FileWritingException {
        File file = new File(filepath);

        try {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs(); // may throw SecurityException
            }
            // may throw IOException
            Files.writeString(file.toPath(), object.serialize(), StandardCharsets.UTF_8);
        } catch (IOException | SecurityException e) {
            throw new FileWritingException(e);
        }
    }

    public static <T> T open(Deserializer<T> deserializer, String filepath)
            throws FileMissingException, FileReadingException, DeserializingException {
        File file = new File(filepath);

        if (!file.exists()) {
            throw new FileMissingException();
        }

        try {
            String s = Files.readString(file.toPath(), StandardCharsets.UTF_8);
            return deserializer.deserialize(s);
        } catch (IOException e) {
            throw new FileReadingException(e);
        }
    }
}
