import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

/**
 * Represents the storage system for the Duke application. The storage system is responsible for
 * loading tasks from the hard disk when the Duke application starts up, and for saving tasks to
 * the hard disk whenever the task list changes.
 */
public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the hard disk when the Duke application starts up.
     * @return a list of String objects representing the tasks in the hard disk.
     * @throws IOException if an error occurs while accessing/creating the directory/file.
     * @throws DukeException if the directory/file did not exist initially.
     */
    public ArrayList<String> load() throws IOException, DukeException {
        boolean isFound = createFile();
        if (!isFound) {
            throw new DukeException();
        }
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        ArrayList<String> lst = new ArrayList<>();
        while (scanner.hasNext()) {
            lst.add(scanner.nextLine());
        }
        scanner.close();
        return lst;
    }

    /**
     * Creates the directory/file containing the tasks, if they did not exist initially.
     * @return a boolean value indicating whether the directory/file existed initially.
     * @throws IOException if an error occurs while accessing/creating the directory/file.
     */
    public boolean createFile() throws IOException {
        boolean isFound = true;
        File file;
        file = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!file.isDirectory()) {
            isFound = false;
            file.mkdirs();
        }
        file = new File(filepath);
        if (file.createNewFile()) {
            isFound = false;
        }
        return isFound;
    }

    /**
     * Saves the tasks in the task list to the hard disk.
     * @param lst the task list.
     * @throws IOException if an error occurs while accessing/creating the directory/file.
     */
    public void save(ArrayList<Task> lst) throws IOException {
        File file = new File(filepath);
        new FileWriter(file, false).close();
        FileWriter filewriter = new FileWriter(file, true);
        for (Task task : lst) {
            filewriter.write(task.getFormattedString() + System.lineSeparator());
        }
        filewriter.close();
    }
}
