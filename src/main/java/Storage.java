import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a class for storage that deals with loading tasks
 * from the file and saving tasks in the file.
 */
public class Storage {
    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from a filePath.
     *
     * @return String data loaded from the file path.
     */
    public String load() throws FileNotFoundException {
        try {
            return FileReading.printFileContents(filePath);
        } catch (FileNotFoundException e) { // if the required file/path directory is not yet created
            System.out.println("Creating the storage file...");

            File f = new File(filePath);

            // create directory if not created
            if (!f.getParentFile().exists()) {
                Storage.createDirectory(f);
            }

            // create file if not created
            if (!f.exists()) {
                Storage.createFile(f);
            }

            // read the file again
            try {
                return FileReading.printFileContents(filePath);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return FileReading.printFileContents(filePath);
    }
    /**
     * Create a directory for the required file path if it's not yet created.
     *
     * @param file File to load the data from.
     * @return nothing.
     */
    public static void createDirectory(File file) {
        file.getParentFile().mkdirs();
    }

    /**
     * Create a file for the required file path if it's not yet created.
     *
     * @param file File to load the data from.
     * @return nothing.
     */
    public static void createFile(File file) {
        try {
            file.createNewFile();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void updateTasks(int count, List<Task> list, String filePath) {
        String output = "";
        for (int i = 1; i < count + 1; i++) {
            Task cur = list.get(i - 1);
            String currentTask = "" + i + "." + cur + "\n";
            output = output + currentTask;
        }
        try {
            FileWriting.writeToFile(filePath, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
