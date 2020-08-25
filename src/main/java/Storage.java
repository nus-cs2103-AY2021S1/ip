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
        boolean exists = helper();
        if (!exists) {
            throw new DukeException();
        }
        File f = new File(filepath);
        Scanner sc = new Scanner(f);
        ArrayList<String> lst = new ArrayList<>();
        while (sc.hasNext()) {
            lst.add(sc.nextLine());
        }
        sc.close();
        return lst;
    }

    /**
     * Creates the directory/file containing the tasks, if they did not exist initially.
     * @return a boolean value indicating whether the directory/file existed initially.
     * @throws IOException if an error occurs while accessing/creating the directory/file.
     */
    public boolean helper() throws IOException {
        boolean exists = true;
        File f;
        f = new File(filepath.substring(0, filepath.lastIndexOf("/")));
        if (!f.isDirectory()) {
            exists = false;
            f.mkdirs();

        }
        f = new File(filepath);
        if (f.createNewFile()) {
            exists = false;
        }
        return exists;
    }

    /**
     * Saves the tasks in the task list to the hard disk.
     * @param lst the task list.
     * @throws IOException if an error occurs while accessing/creating the directory/file.
     */
    public void save(ArrayList<Task> lst) throws IOException {
        File f = new File(filepath);
        new FileWriter(f, false).close();
        FileWriter fw = new FileWriter(f, true);
        for (Task t : lst) {
            fw.write(t.formattedString() + System.lineSeparator());
        }
        fw.close();
    }
}
