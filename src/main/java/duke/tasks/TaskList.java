package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.parser.Parser;

/**
 * Represents a list of all tasks the user
 * wants to keep track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an instance of a list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Creates an instance of a list of tasks from
     * a file containing saved tasks.
     *
     * @param file File that user wants tasks to be loaded from.
     */
    public TaskList(File file) {
        tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split("\\|");
                Task newTask = getDataFromLine(line);
                boolean isTaskDone = line[1].strip().equals("1");
                boolean hasReminder = line[2].strip().equals("1");
                if (isTaskDone) {
                    newTask.setDone();
                }
                if (hasReminder) {
                    newTask.setReminder();
                }
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Task getDataFromLine(String[] line) {
        Task newTask;
        LocalDate localDate;
        switch (line[0].strip()) {
        case "T":
            newTask = new ToDo(line[3].strip());
            break;
        case "D":
            localDate = Parser.parseDate(line[4].strip());
            if (localDate != null) {
                newTask = new Deadline(line[3].strip(), localDate);
            } else {
                newTask = new Deadline(line[3].strip(), line[4].strip());
            }
            break;
        case "E":
            localDate = Parser.parseDate(line[4].strip());
            if (localDate != null) {
                newTask = new Event(line[3].strip(), localDate);
            } else {
                newTask = new Event(line[3].strip(), line[4].strip());
            }
            break;
        default:
            newTask = null;
        }
        return newTask;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addToList(Task task) throws DukeException {
        if (task == null) {
            throw new DukeException();
        }
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex Index of task to be removed.
     */
    public void removeFromList(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid Index!");
        }
        tasks.remove(taskIndex);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks in the list.
     */
    public int getListSize() {
        return tasks.size();
    }

    /**
     * Returns the task at a particular index.
     *
     * @param taskIndex Index of task to be retrieved.
     * @return Retrived task.
     */
    public Task getTaskAtIndex(int taskIndex) throws IndexOutOfBoundsException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid Index!");
        }
        return tasks.get(taskIndex);
    }
}
