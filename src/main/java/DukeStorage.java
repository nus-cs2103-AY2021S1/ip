import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the storage for tasks in the Duke software.
 */

public class DukeStorage {
    private String filePath;

    /**
     * Constructor for storage.
     * @param filePath savepath of file.
     */
    public DukeStorage(String filePath) {
        this.filePath = filePath;
        createStorage();
    }

    /**
     * Initialises the tasklist using the current storage in the file.
     */
    public void createStorage() {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            if (file.createNewFile()) {
                System.out.println("Duke storage is ready to use :-)");
            } else {
                System.out.println("Duke storage already exists, good to go!");
            }
        } catch (IOException ex) {
            System.out.println("Error!");
            ex.printStackTrace();
        }
    }

    /**
     * Decodes and load all the tasks in the file into the tasklist.
     * @param tasks tasklist to store tasks.
     * @throws FileNotFoundException If file does not exist in the proper folder.
     * @throws DukeException If invalid date/time is given.
     */
    public void reloadStorage(List<Task> tasks) throws FileNotFoundException, DukeException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            // decode and add the task
            String line = sc.nextLine();
            tasks.add(DukeInterpreter.decode(line));
        }
    }

    /**
     * Encodes and save all the tasks from the tasklist into the file.
     * @param tasks tasklist to store tasks.
     * @throws IOException If an input/output operation fails.
     */
    public void saveStorage(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : tasks) {
            writer.write(DukeInterpreter.encode(task) + "\n");
        }
        writer.close();
    }
}
