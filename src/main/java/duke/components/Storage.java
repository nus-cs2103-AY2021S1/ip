package duke.components;

import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Storage class handles the creation of an instance of a Storage object that stores and loads data.
 * the 'Storage' class supports operators.
 * Supported operators includes (i) returning the arraylist taken from the file loaded
 * and (ii) overwriting the file with a new list of tasks
 */

public class Storage {

    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) throws FileNotFoundException, DukeException {

        File taskFile = new File(filePath);

        if (!taskFile.exists()) {

        } else {

            Scanner List = new Scanner(taskFile);

            while (List.hasNextLine()) {
                String task = List.nextLine();
                Task currentTask = null;
                if (task.charAt(1) == 'T') {
                    currentTask = new ToDo("todo " + task.substring(8));
                    taskList.add(currentTask);

                } else if (task.charAt(1) == 'D') {

                    currentTask = new Deadline(
                            "deadline " + task.substring(
                                    8,
                                    task.indexOf('(') - 1
                            ),
                            LocalDate.parse(task.substring(
                                    task.indexOf('(') + 5,
                                    task.indexOf(')')))
                    );

                    taskList.add(currentTask);

                } else if (task.charAt(1) == 'E') {

                    currentTask = new Event(
                            "event " + task.substring(
                                    8,
                                    task.indexOf('(') - 1
                            ),
                            LocalDate.parse(task.substring(
                                    task.indexOf('(') + 5,
                                    task.indexOf('(') + 15)
                            ),
                            task.substring(
                                    task.indexOf('(') + 16,
                                    task.indexOf(')'))
                    );

                    taskList.add(currentTask);
                }
                if (task.charAt(4) == '\u2713') {
                    assert currentTask != null;
                    currentTask.finishTask();
                }

            }

        }

    }

    /**
     * accesses the task list from the file loaded.
     *
     * @return task list as an arraylist.
     */

    public ArrayList<Task> load() {
        return taskList;
    }

    /**
     * overwrites the current file with a new task list.
     *
     * @param newTaskList new task list to be used to overwrite.
     */
    public void overwriteWith(ArrayList<Task> newTaskList) throws IOException {
        this.taskList = newTaskList;
        PrintWriter file = new PrintWriter("duke.txt", "UTF-8");
        BufferedWriter writer = new BufferedWriter(file);
        for (int i = 0; i < taskList.size(); i++) {

            writer.write(taskList.get(i).toSave());
            writer.newLine();

        }
        writer.close();

    }
}
