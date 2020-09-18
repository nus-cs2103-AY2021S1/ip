package duke.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Responsible for saving and loading the state of the application from a file.
 */
public class StorageManager {
    /** Path of the file to read from and write to. */
    private final String filePath;

    /**
     * Constructs a new {@code StorageManager} object that is associated with the specified file.
     *
     * @param filePath the path of the file to read from and write to.
     */
    public StorageManager(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes data to the specified file, overwriting the file if it already exists.
     *
     * @param data the data to be written into the file.
     * @throws IOException if an I/O error occurs.
     */
    public void saveToFile(String data) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        // If the parent directory does not exist, create it along with any other
        // necessary but non-existent parent directories.
        if (!parentDir.exists()) {
            parentDir.mkdirs();
        }
        // Set the encoding to UTF-8.
        Writer writer = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
        writer.write(data);
        writer.close();
    }

    /**
     * Reads data from the specified file.
     *
     * @return the data present in the file or a string representation of an empty array if the file does not exist.
     * @throws IOException if an I/O error occurs.
     */
    public String readFromFile() throws IOException {
        // If the file does not exist, return a string representation of an empty array.
        if (!(new File(filePath).exists())) {
            return "[]";
        }
        return Files.readString(Path.of(filePath));
    }
}
