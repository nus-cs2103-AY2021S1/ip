package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tool.TaskList;

/**
 * Represents a storage manager to handle file I/O.
 */
public class Storage {

    /**
     * String representation of path that stores user data
     */
    protected String dataPath;

    /**
     * Creates a storage to certain path.
     *
     * @param dataPath Data path (relative).
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
    }

    /**
     * Loads the task list from stored file.
     *
     * @return An array list that represents the task list.
     * @throws IOException An exception when the system cannot create the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            File dataFile = new File(dataPath);
            Scanner sc = new Scanner(dataFile);

            //Read the file
            while (sc.hasNextLine()) {
                String nowTask = sc.nextLine();
                String[] taskComponents = nowTask.split(" / ");

                //Decode the file representation of list
                handleStorageFormat(taskComponents, taskList);

            }
        } catch (FileNotFoundException e) {
            if (!new File("data").mkdir() || !new File(dataPath).createNewFile()) {
                System.out.println("error");
            }
        }

        return taskList;
    }

    /**
     * Saves the changes to the storage path.
     *
     * @param taskList Current task list in the system.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter("data/duke.txt", false);

            assert taskList != null;
            //Write the list to the file
            for (Task task : taskList.getTasks()) {
                writer.write(task.toFileStringFormat() + '\n');
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleStorageFormat(String[] taskComponents, List<Task> taskList) {
        switch (taskComponents[0]) {
        case "T":
            taskList.add(new Todo(taskComponents[1].equals("1"), taskComponents[2]));
            break;
        case "E":
            taskList.add(new Event(taskComponents[1].equals("1"), taskComponents[2],
                    LocalDateTime.parse(taskComponents[3])));
            break;
        case "D":
            taskList.add(new Deadline(taskComponents[1].equals("1"), taskComponents[2],
                    LocalDateTime.parse(taskComponents[3])));
            break;
        default:
            assert false;
        }
    }
}
