package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.deadline.Deadline;
import duke.task.eventtask.EventTask;
import duke.task.todo.ToDo;
import duke.tasklist.TaskList;

/**
 * Storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;
    private Scanner lineReader;

    /**
     * Constructor for creating a storage variable. It will create a new tasklist.txt if it does not exist, or
     * use existing one if it is pressent.
     *
     * @param file Contains the filepath to wear the tasklist.txt will be stored.
     */
    public Storage(File file) {
        this.file = file;
        try {
            file.createNewFile();
            this.lineReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If a tasklist.txt file exists, this method would load it into the TaskList.
     *
     * @return A TaskList that is loaded with the tasks recorded on the txt file.
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        while (lineReader.hasNextLine()) {
            String data = lineReader.nextLine();
            tasks.add(taskCreator(data));
        }
        return tasks;
    }

    /**
     * Updates the Tasklist.txt file with the existing tasks and states in the tasklist.
     *
     * @param taskList the TaskList that we are updating.
     * @throws IOException if the file cannot be found.
     */
    public void updateFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        for (int i = 0; i < taskList.getSize(); i++) {
            fw.write(taskList.getTask(i).toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Creates a different type of task based on the response given
     *
     * @param task input by the user
     * @return a new task child object that is the type that is indicated in the response.
     */
    private Task taskCreator(String task) {
        switch (task.charAt(1)) {
        case 'T':
            return new ToDo(task);
        case 'D':
            return new Deadline(task);
        case 'E':
            return new EventTask(task);
        default:
            assert false : "no such task from taskCreator";
            return null;
        }
    }
}
