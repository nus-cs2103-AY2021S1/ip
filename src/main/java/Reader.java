import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Represents a class to handle read-from-disk operations.
 */
public class Reader {
    /**
     * Checks if a file exists.
     *
     * @param path The file path to check.
     * @return True if the file exists, False otherwise.
     */
    static boolean doesFileExist(String path) {
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    /**
     * Reads a serialized TaskList instance from disk.
     *
     * @param path The file path of the serialized TaskList.
     * @return The deserialized TaskList instance.
     * @throws ClassNotFoundException if the file given doesn't contain a serialized TaskList instance.
     * @throws IOException if an IO exception occurs while reading the file.
     */
    static TaskList readListFromFile(String path) throws ClassNotFoundException, IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        return (TaskList) objectInputStream.readObject();
    }
}
