package duke.main;

import duke.exception.EmptyTaskException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingDateException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private final List<Task> tasks;

    TaskList(File file) {
        this.tasks = initTasks(file);
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
                    System.out.println(Ui.LINE + "\n"
                            + "Hmm, something's wrong with this task\n"
                            + Ui.LINE);
                    break;
                }
                if (task != null) {
                    if (strings[1].equals("1")) task.setDone();
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
    String write() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            stringBuilder.append(task.print()).append(System.lineSeparator());
        }
        if (stringBuilder.length() != 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }

    /**
     * Adds a Task object to the list based on a user command.
     * If the command starts with "todo" a ToDo task is added.
     * If it starts with "deadline" a DeadLine task is added.
     * If it starts with "event" an Event task is created.
     *
     * @param task Command describing the kind of task and its details.
     * @return Newly added task.
     * @throws MissingDateException If a date is not provided for an Event or Deadline.
     * @throws InvalidDateException If the date provided is not in the correct format.
     * @throws InvalidCommandException If the command does not match either Tasks (case sensitive).
     * @throws EmptyTaskException If no task description is provided in the command.
     */
    Task add(String task) throws MissingDateException,
            InvalidDateException, InvalidCommandException, EmptyTaskException {
        Task newTask;
        if (task.startsWith("todo")) {
            newTask = ToDo.create(task);
        } else if (task.startsWith("deadline")) {
            newTask = Deadline.create(task);
        } else if (task.startsWith("event")) {
            newTask = Event.create(task);
        } else {
            throw new InvalidCommandException();
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Removes a Task object from the list at the specified index.
     *
     * @param index Position on the TaskList representing the element to be removed.
     * @return Deleted Task object represented by the specified index.
     * @throws InvalidIndexException If index lies outside the boundaries of the list.
     */
    Task delete(int index) throws InvalidIndexException {
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
    Task complete(int index) throws InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidIndexException();
        }
        Task task = tasks.get(index);
        task.setDone();
        return task;
    }

    /**
     * Returns the length of the list of Tasks.
     *
     * @return Length of TaskList contents.
     */
    int size() {
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
