package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.InvalidIndexException;
import duke.exception.UnreadableSaveTaskException;
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

    /**
     * Constructs a new TaskList object by reading the data in the given saved file.
     *
     * @param file File representing location of the saved tasks.
     */
    public TaskList(File file) {
        this.tasks = initTasks(file);
        assert (tasks != null) : "Tasks not initialised";
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
        assert file.exists() : "Save file should have been created";
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();
            while (sc.hasNext()) {
                String current = sc.nextLine();
                String[] strings = current.split(" \\| ");
                Task task = getTaskFromLine(strings);

                if (task != null) {
                    tasks.add(task);
                }
            }

            return tasks;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Task getTaskFromLine(String[] data) {
        try {
            Task task;
            switch (data[0]) {
            case "T":
                task = ToDo.createFromFile(data);
                break;
            case "D":
                task = Deadline.createFromFile(data);
                break;
            case "E":
                task = Event.createFromFile(data);
                break;
            default:
                throw new UnreadableSaveTaskException();
            }

            if (data[1].equals("1")) {
                task.setDone();
            }

            return task;
        } catch (UnreadableSaveTaskException e) {
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
        if (tasks.isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.toDataString())
                    .append(System.lineSeparator());
        }

        return stringBuilder.toString();
    }

    /**
     * Adds a new Task to the list.
     *
     * @param task New task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
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
     * Returns true if and only if the task exists in the list.
     *
     * @param task Task to be detected in the task list.
     * @return True if the task exists in the list, false otherwise.
     */
    public boolean contains(Task task) {
        return tasks.contains(task);
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
        if (tasks.isEmpty()) {
            return "You have no tasks";
        }

        StringBuilder list = new StringBuilder();
        int number = 1;
        for (Task task : tasks) {
            list.append(String.format("%d.", number++))
                    .append(task)
                    .append(System.lineSeparator());
        }

        return list.toString();
    }
}
