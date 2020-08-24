import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage object used to manage saved data.
 */
public class Storage {

    private final String filePath;

    protected Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the saved content from the filePath.
     *
     * @return Scanner object that can be used to read the file.
     */
    protected Scanner load() throws FileNotFoundException {
        File file = new File(filePath);
        return new Scanner(file);
    }

    /**
     * Saves the updated tasks into the file.
     *
     * @param tasks The String of updated tasks to be saved.
     */
    protected void saveTasks(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }

}
