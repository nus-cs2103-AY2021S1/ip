package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * The Storage class handles all the reading and writing between
 * the program and the txt file which stores all the tasks in a
 * comma-separated value (csv) format. Each time the task list in
 * the program is being updated, the Storage object will rewrite
 * the txt file.
 */
public class Storage {

    private final File file;

    /**
     * Initialises with the File object using the txt file path.
     * If the path does not exist due to missing directories,
     * this constructor will attempt to create the necessary
     * directories.
     * @param filePath the path of the txt file.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
            } catch (IOException ioe) {
                System.err.println("IOException: " + ioe.getMessage());
            }
        }
    }

    /**
     * Reads the txt file and creates the tasks accordingly. The parsing of
     * the csv values in the file is hardcoded since writing to the file has
     * a standard format:
     *
     *     Subtype , Description , DateTime
     *
     * Subtypes are single letters - T/D/E
     * Descriptions are strings
     * DateTime are raw date time strings for Deadline and Event only.
     * @return the list of tasks to be loaded into TaskList.
     * @throws DukeException when task creation fails.
     */
    public List<Task> load() throws DukeException {
        List<Task> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] t = sc.nextLine().split(",");

                switch (t[0]) {
                case "T":
                    list.add(new Todo(t[2], t[1].equals("1")));
                    break;
                case "D":
                    list.add(new Deadline(t[2], t[3], t[1].equals("1")));
                    break;
                case "E":
                    list.add(new Event(t[2], t[3], t[1].equals("1")));
                    break;
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return list;
    }

    /**
     * Since updating of the list may involve a combination of adding, removing,
     * and mutating of values, the txt file is constantly being rewritten from
     * scratch in this update method. Since task numbers are relatively low for
     * individual users, this approach is not too detrimental to performance.
     * @param list the list of task to be read and written into txt file.
     */
    public void update(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task t: list) {
                String toWrite = String.format("%s,%s", t.getType(), (t.getDone() ? "1" : "0"));
                String desc = t.getDescription();
                if ("DE".contains(t.getType())) {
                    String[] descSplit = desc.split(" / ");
                    toWrite += "," + descSplit[descSplit.length - 2];
                    toWrite += "," + descSplit[descSplit.length - 1];
                } else {
                    toWrite += "," + desc;
                }
                fw.write(toWrite + "\n");
            }
            fw.close();
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }
}
