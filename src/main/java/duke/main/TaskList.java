package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class representing a list of tasks.
 * It supports operations for removing, completing, adding and searching for tasks.
 */
public class TaskList {
    private final List<Task> tasks;

    public TaskList(File file) {
        this.tasks = initTasks(file);
    }

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Generates a list of tasks from a file's contents.
     * The method reads each line of the file representing a task and converts it into its
     * respective Task object.
     *
     * @param file File to be read from.
     * @return List of all Task objects saved in the file.
     */
    private static List<Task> initTasks(File file) {
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String current = sc.nextLine();
                String[] strings = current.split(" \\| ");
                Task task = null;
                switch (strings[0]) {
                case "T":
                    task = ToDo.createFromFile(strings[2]);
                    break;
                case "D":
                    task = Deadline.createFromFile(strings[2], strings[3]);
                    break;
                case "E":
                    task = Event.createFromFile(strings[2], strings[3]);
                    break;
                default:
                    System.out.println("Hmm, something's wrong with this task");
                    break;
                }
                if (task != null) {
                    if (strings[1].equals("1")) {
                        task.setDone();
                    }
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Formats TaskList into a string to be written in a file.
     *
     * @return String representation of a saved TaskList.
     */
    public String toSaveFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toDataString())
                    .append(System.lineSeparator());
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * Creates a new ToDo instance and adds it to the list.
     * @param details Description of task to create.
     * @return Newly-created task.
     */
    public ToDo addToDo(String details) {
        ToDo toDo = ToDo.create(details);
        tasks.add(toDo);
        return toDo;
    }

    /**
     * Creates a new Event instance and adds it to the list.
     * @param details Description of task to create.
     * @return Newly-created task.
     * @throws MissingDateException If date field is missing.
     * @throws InvalidDateException If date is not provided in the correct format.
     */
    public Event addEvent(String details)
            throws MissingDateException, InvalidDateException {
        Event event = Event.create(details);
        tasks.add(event);
        return event;
    }

    /**
     * Creates a new Deadline instance and adds it to the list.
     * @param details Description of task to create.
     * @return Newly-created task.
     * @throws MissingDateException If date field is missing.
     * @throws InvalidDateException If date is not provided in the correct format.
     */
    public Deadline addDeadline(String details)
            throws MissingDateException, InvalidDateException {
        Deadline deadline = Deadline.create(details);
        tasks.add(deadline);
        return deadline;
    }

    /**
     * Removes a Task object from the list at the specified index.
     *
     * @param index Position on the TaskList representing the element to be removed.
     * @return Deleted Task object represented by the specified index.
     * @throws InvalidIndexException If index lies outside the boundaries of the list.
     */
    public Task delete(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        return tasks.remove(index);
    }

    /**
     * Marks a Task object in the list at the specified index as done.
     *
     * @param index Position on the TaskList representing element to be marked done.
     * @return Completed Task object represented by the specified index.
     * @throws InvalidIndexException If index lies outside boundaries of the list.
     */
    public Task complete(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        return task;
    }

    /**
     * Returns a TaskList containing all tasks with a given keyword in the description.
     *
     * @param search Keyword to filter tasks by.
     * @return TaskList containing all tasks that match the keyword.
     */
    public TaskList find(String search) {
        List<Task> filtered = new ArrayList<>();
        for (Task task : tasks) {
            if (task.contains(search)) {
                filtered.add(task);
            }
        }
        return new TaskList(filtered);
    }

    /**
     * Returns the length of the list of Tasks.
     *
     * @return Length of TaskList contents.
     */
    public int size() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder list = new StringBuilder();
        int number = 1;
        for (Task task : tasks) {
            list.append(String.format("%d.", number++))
                    .append(task)
                    .append(System.lineSeparator());
        }
        if (list.length() != 0) {
            list.deleteCharAt(list.length() - 1);
        }
        return list.toString();
    }
}
