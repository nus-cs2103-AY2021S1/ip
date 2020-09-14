package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>The duke.storage.FileReadWrite class handles the writing to and reading
 * from saved file with static methods.</p>
 */
public class FileReadWrite {
    private static String home = System.getProperty("user.home");
    private static java.nio.file.Path path = java.nio.file.Paths.get(home, "iP", "SavedData");
    private static String fullFilePath = path + "/SavedData.txt";
    private static boolean directoryExists = java.nio.file.Files.exists(path);

    /**
     * Writes to a file.
     * <li>If the file does not exist, create a new file then write to the file.</li>
     * <li>If the file exists, write to the existing file.</li>
     *
     * @param textToAdd A String representing the text to write to file.
     * @throws IOException thrown when Input/Output operation fails.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        if (!directoryExists) {
            Files.createDirectories(path);
        }
        FileWriter fw = new FileWriter(fullFilePath);
        fw.write(textToAdd + System.lineSeparator());
        fw.flush();
        fw.close();
    }

    /**
     * Append text to an existing file.
     *
     * @param textToAppend A String representing the text to append to file.
     * @throws IOException thrown when Input/Output operation fails.
     */
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    /**
     * Loads all texts, line by line, from a saved file and stores them in a list of String.
     *
     * @return A list of String representing the data in the file.
     * @throws IOException thrown when Input/Output operation fails.
     */
    public static List<String> loadFromSavedFile() throws IOException {
        File file = new File(fullFilePath);
        if (file.exists()) {
            return Files.readAllLines(Paths.get(fullFilePath));
        } else {
            return null;
        }
    }
}
