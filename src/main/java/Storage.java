import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage system of the Duke application. The storage system is
 * responsible for loading tasks from the hard disk whenever the Duke application
 * starts up, and for saving tasks in the hard disk whenever the task list changes.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the hard disk when the Duke application starts up.
     * @return List of String objects representing the tasks in the hard disk.
     * @throws IOException If an error occurs while accessing/creating the
     * directory/file containing the tasks
     * @throws DukeException If the directory/file containing the tasks
     * did not exist initially.
     */
    public ArrayList<String> load() throws IOException, DukeException {
        boolean isDirectoryAndFileFound = createFile();
        if (!isDirectoryAndFileFound) {
            throw new DukeException();
        }
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            tasks.add(scanner.nextLine());
        }
        scanner.close();
        return tasks;
    }

    /**
     * Creates the directory/file containing the tasks, if they did not exist initially.
     * @return Boolean value indicating whether the directory/file existed initially.
     * @throws IOException If an error occurs while accessing/creating the directory/file.
     */
    public boolean createFile() throws IOException {
        boolean isDirectoryAndFileFound = true;
        File file = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!file.isDirectory()) {
            isDirectoryAndFileFound = false;
            file.mkdirs();
        }
        file = new File(filepath);
        if (file.createNewFile()) {
            isDirectoryAndFileFound = false;
        }
        return isDirectoryAndFileFound;
    }

    /**
     * Saves the tasks in the task list to the hard disk.
     * @param lst Task list.
     * @throws IOException If an error occurs while accessing/creating the directory/file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        File file = new File(filepath);
        new FileWriter(file, false).close();
        FileWriter filewriter = new FileWriter(file, true);
        for (Task task : tasks) {
            filewriter.write(task.getFormattedString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
