import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileReadWrite {
    private static String home = System.getProperty("user.home");
    private static java.nio.file.Path path = java.nio.file.Paths.get(home, "iP", "SavedData");
    private static String fullFilePath = path + "/SavedData.txt";
    private static String fileName = "/SavedData.txt";
    private static boolean directoryExists = java.nio.file.Files.exists(path);

    public void print() {
        System.out.println(path);
        System.out.println(directoryExists);
    }

    public static void writeToFile(String textToAdd) throws IOException {

        // if file not existed, create new then add
        if (!directoryExists) {
            Files.createDirectories(path);
        }

        FileWriter fw = new FileWriter(fullFilePath);
        fw.write(textToAdd + System.lineSeparator());
        fw.flush();
        fw.close();
    }

    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(fullFilePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }

    public static List<String> loadFromSavedFile() throws IOException {
        File file = new File(fullFilePath);
        if (file.exists()) {
            return Files.readAllLines(Paths.get(fullFilePath));
        } else {
            return null;
        }
    }
}
