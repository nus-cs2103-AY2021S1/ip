package duke.tasks;

import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

import duke.parser.Parser;

/**
 * Represents a list of all tasks the user
 * wants to keep track of.
 */
public class TaskList {
    private ArrayList<Task> tasks;

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
                Task newTask = null;
                LocalDate localDate = null;
                switch (line[0].strip()) {
                case "T":
                    newTask = new ToDo(line[2].strip());
                    break;
                case "D":
                    localDate = Parser.parseDate(line[3].strip());
                    if (localDate != null) {
                        newTask = new Deadline(line[2].strip(), localDate);
                    } else {
                        newTask = new Deadline(line[2].strip(), line[3].strip());
                    }
                    break;
                case "E":
                    localDate = Parser.parseDate(line[3].strip());
                    if (localDate != null) {
                        newTask = new Event(line[2].strip(), localDate);
                    } else {
                        newTask = new Event(line[2].strip(), line[3].strip());
                    }
                    break;
                }
                if (line[1].strip().equals("1")) {
                    newTask.setDone();
                }
                tasks.add(newTask);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates an instance of a list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addToList(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param taskIndex Index of task to be removed.
     */
    public void removeFromList(int taskIndex) {
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
    public Task getTaskAtIndex(int taskIndex) {
        return tasks.get(taskIndex);
    }
}
