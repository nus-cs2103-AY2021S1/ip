import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a storage class that allows data to be stored and
 * retrieved in/from a local file.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class Storage {
    /**
     * The path of the storage file.
     */
    private String filePath;

    /**
     * Storage constructor.
     *
     * @param filePath The path of the local file.
     */
    public Storage(final String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from a local file and returns it as a TaskList.
     *
     * @return The TaskList loaded from the file.
     * @throws DukeException When there is a problem finding/creating the file.
     */
    public TaskList load() throws DukeException {
        File directory = new File("data/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = new File(this.filePath);
        if (f.exists()) {
            try {
                TaskList taskList = new TaskList();
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    String taskString = s.nextLine();
                    if (!taskString.isEmpty()) {
                        Parser.parseTaskFromFile(taskString, taskList);
                    } else {
                        continue;
                    }
                }
                return taskList;
            } catch (FileNotFoundException fnfe) {
                throw new DukeException(fnfe.getMessage());
            }
        } else { //file f doesn't exist
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
            TaskList taskList = new TaskList();
            return taskList;
        }
    }

    /**
     * Saves data from the program into a local file.
     *
     * @param listToAdd         This is the task list to be saved.
     * @throws DukeException    When there is a problem writing to local file.
     */
    public void write(final TaskList listToAdd) throws DukeException {
        try {
            FileWriter resetfw = new FileWriter(this.filePath);
            resetfw.write("");
            resetfw.close();

            FileWriter fw = new FileWriter(this.filePath, true);
            for (int i = 0; i < listToAdd.size(); i++) {
                String task = listToAdd.get(i).toString();
                task = task.replace("(", "/");
                task = task.replace(")", "");
                task = task.replace(":", "");
                fw.write("\n" + task);
            }
            fw.close();
        } catch (IOException ioe) {
            throw new DukeException(ioe.getMessage());
        }
    }
}
