package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.util.ArrayList;

/**
 * Stores the location of the saved TaskList.
 */

public class Storage {
    private final String filepath;

    public Storage() {
        this.filepath = "";
    }

    /**
     * Creates a new storage with the specified directory.
     * @param filepath directory to save to and load from
     */

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the previously saved tasks into an arraylist.
     * If there is no saved directory or file, create a new folder and arraylist.
     */

    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            String str;
            while ((str = reader.readLine()) != null) {
                Task T;
                if (str.startsWith("T")) {
                    T = ToDo.load(str);
                } else if (str.startsWith("E")) {
                    T = Event.load(str);
                } else if (str.startsWith("D")) {
                    T = Deadline.load(str);
                } else {
                    break;
                }
                tasks.add(T);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Starting a new task list");
            File file = new File("./data");
            file.mkdir();
            return tasks;
        } catch (IOException e) {
            e.printStackTrace();
            return tasks;
        }
    }

    /**
     * Saves the current list of tasks into the directory.
     * @param tasks Current list of tasks.
     */

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filepath);
            for (Task t : tasks.getTasks()) {
                fw.write(t.store() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("error in saving");
        }
    }

}
