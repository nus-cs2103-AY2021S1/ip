import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage system of the Duke application. The storage system is responsible
 * for loading tasks from the hard disk whenever the Duke application starts up, and for
 * saving tasks in the hard disk whenever the task list changes.
 */
public class Storage {

    private String filepath;

    /**
     * Constructs a Storage object with the specified location for containing the tasks.
     * @param filepath Path of file containing the tasks.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads tasks from the hard disk whenever the Duke application starts up.
     * @return List of String objects representing the tasks in the hard disk.
     * @throws IOException If an error occurs while accessing or creating the directory
     * or file containing the tasks.
     * @throws DukeException If the directory or file containing the tasks does not
     * exist yet.
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
     * Creates the directory or file containing the tasks, if it does not exist yet.
     * @return Boolean value indicating whether the directory or file already exists.
     * @throws IOException If an error occurs while accessing or creating the directory or
     * file containing the tasks.
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
     * Saves the tasks in the specified task list to the hard disk.
     * @param tasks Task list.
     * @throws IOException If an error occurs while accessing the directory or file containing
     * the tasks.
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
