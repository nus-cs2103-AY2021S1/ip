package duke;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DateException;
import duke.format.DateFormat;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;



/**
 * Represents a storage system responsible for loading existing tasks
 * when user starts up the program and saving all the tasks on hard drive
 * when user exits the program.
 */
public class Storage {
    private final String filePath;
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates an instance of storage with the file location of
     * where the tasks are stored.
     * @param filePath the file location where the tasks are stored on hard drive
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads up all the existing tasks on hard drive and returns them
     * in the form of a list.
     * @return a list of existing tasks on hard drive
     * @throws IOException If there are errors reading the storage file.
     * @throws DateException If the date of tasks in the file are of the wrong format.
     */
    public List<Task> load() throws IOException, DateException {

        File dataFile = new File(this.filePath);
        if (!dataFile.exists()) {
            return taskList;
        }
        FileReader reader = new FileReader(this.filePath);
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {
            loadTask(line);
            line = br.readLine();
        }
        reader.close();
        return this.taskList;
    }

    /**
     * Saves all the tasks into hard drive when user exists the program so
     * that they can be loaded again when the user runs the program again.
     * @param taskList the current list of tasks
     * @throws IOException If there are errors writing tasks to the storage file.
     */
    public void saveData(TaskList taskList) throws IOException {

        File dataFile = new File(this.filePath);
        String directoryPath = dataFile.getParent();
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        dataFile.createNewFile();
        FileWriter writer = new FileWriter(this.filePath);
        String data = "";
        for (Task task : taskList.getTasks()) {
            data += String.format("%s\n", formatTask(task));
        }
        writer.write(data);
        writer.close();

    }

    /**
     * Formats the task into an understandable string to be stored on hard drive.
     * @param task the task that needs to be converted to string format
     * @return the string format of input task
     */
    private String formatTask(Task task) {
        String name = task.getName();
        int isComplete = task.getStatus() ? 1 : 0;
        if (task.getType() == TaskType.DEADLINE) {
            return String.format("%s|%d|%s|%s", "D", isComplete,
                    name, DateFormat.formatDate(task.getDate()));
        } else if (task.getType() == TaskType.TODO) {
            return String.format("%s|%d|%s", "T", isComplete, name);
        } else {
            return String.format("%s|%d|%s|%s", "E", isComplete,
                    name, DateFormat.formatDate(task.getDate()));
        }
    }

    /**
     * Takes the string stored in hard drive and converts it back to
     * a task object, before adding it to the existing list of tasks.
     * @param line the string format of task
     * @throws DateException If the date of tasks stored in hard drive is of the wrong format.
     */
    private void loadTask(String line) throws DateException {
        String[] taskComponent = line.split("\\|");
        boolean isComplete = !taskComponent[1].equals("0");
        String name = taskComponent[2];
        if (taskComponent[0].equals("T")) {
            taskList.add(new Todo(name, isComplete));
        } else if (taskComponent[0].equals("D")) {
            String by = taskComponent[3];
            taskList.add(new Deadline(name, isComplete, DateFormat.parseDate(by)));
        } else {
            String at = taskComponent[3];
            taskList.add(new Event(name, isComplete, DateFormat.parseDate(at)));
        }
    }
}
