package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Storage class will save changes made to the task list and load user's existing tasks when ran.
 */
public class Storage {

    /** File path */
    private final String path;

    /**
     * Initialises storage to a designated general file path
     */
    public Storage() {
        this.path = "data/listOfTasks.txt";
    }

    /**
     * Loads data from the file to the bot when the bot just started running.
     *
     * @param tasks
     */
    public void load(TaskList tasks) {
        try {
            File file = new File(this.path);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                String[] details = line.split(" \\| ");
                boolean isDone = details[1].equals("1") ? true : false;
                switch (details[0]) {
                case "T":
                    tasks.addStoredTask(new ToDo(details[2], isDone));
                    break;
                case "D":
                    tasks.addStoredTask(new Deadline(details[2], details[3], isDone));
                    break;
                case "E":
                    tasks.addStoredTask(new Event(details[2], details[3], isDone));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + details[0]);
                }
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            System.err.println (e);
        }
    }

    /**
     * Saves changes of the list to the file when bot stopped running.
     *
     * @param tasks
     */
    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(this.path);
            file.getParentFile().mkdirs();
            FileWriter fw;
            fw = file.exists()
                    ? new FileWriter(file, false)
                    : new FileWriter(file, true);
            for (Task task : tasks) {
                fw.write(task.writeToFile() + "\n");
            }
            fw.close();
            System.out.println("All changes had been saved.");
        } catch (IOException e) {
            System.err.println (e);
        }
    }
}
