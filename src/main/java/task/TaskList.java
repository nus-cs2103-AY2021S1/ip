package main.java.task;

import main.java.exception.InvalidArgumentException;
import main.java.misc.Parser;
import main.java.misc.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> database = new ArrayList<>();

    /**
     * Create a new task corresponding to the tokens then add it into the list of task, or throw an exception
     * if the input tokens is invalid.
     * @param tokens the description for the task (e.g. type, time)
     * @return the task created
     * @throws InvalidArgumentException
     */
    public Task addTask(List<String> tokens) throws InvalidArgumentException {
        Task task;
        String datetimeString = tokens.get(2).trim();
        LocalDateTime datetime = (datetimeString.equals("null"))
                ? LocalDateTime.MIN
                : Parser.stringToTime(datetimeString);

        switch (tokens.get(0)) {
            case "todo":
                task = new Todo(tokens.get(1).trim());
                break;
            case "deadline":
                if (datetimeString.equals("null")) throw new InvalidArgumentException("Deadline's time cannot be empty");
                task = new Deadline(tokens.get(1).trim(), datetime);
                break;
            case "event":
                if (datetimeString.equals("null")) throw new InvalidArgumentException("Event's time cannot be empty");
                task = new Event(tokens.get(1).trim(), datetime);
                break;
            default:
                throw new Error("An unexpected error has occurred");
        }
        if (tokens.get(3).equals("1")) {
            task.markAsDone();
        }
        database.add(task);
        return task;
    }

    /**
     * Gets the task from the list with the given index, or throw an exception if the index is invalid.
     * @param index the query index
     * @return the corresponding task
     * @throws InvalidArgumentException
     */
    public Task getTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Invalid argument for the LIST command.");
        }
        return database.get(index - 1);
    }

    /**
     * Marks the task from the list with the given index as done, or throw an exception if the index is invalid.
     * @param index the query index
     * @throws InvalidArgumentException
     */
    public void finishTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Out of range argument for DONE command.");
        }
        database.get(index - 1).markAsDone();
    }

    /**
     * Removes the task from the list with the given index, or throw an exception if the index is invalid.
     * @param index the query index
     * @return the task that has just been deleted
     * @throws InvalidArgumentException
     */
    public Task removeTask(int index) throws InvalidArgumentException {
        if (index <= 0 || index > database.size()) {
            throw new InvalidArgumentException("Out of range argument for DELETE command.");
        }
        return database.remove(index - 1);
    }

    /**
     * Initializes the list of task with the information from the disk.
     * @throws InvalidArgumentException
     */
    public void initialize() throws InvalidArgumentException {
        List<List<String>> data = Storage.readFile();
        for (List<String> tokens : data) {
            addTask(tokens);
        }
    }

    /**
     * Saves the list of task into the disk.
     */
    public void save() {
        Storage.writeFile(database);
    }

    /**
     * Counts the number of current tasks in the list.
     * @return the number of current tasks.
     */
    public int count() {
        return database.size();
    }

    /**
     * Clears all tasks in the list.
     */
    public void clearAll() {
        database.clear();
    }

    /**
     * Return all task in the list in the form of strings.
     * @return the list of corresponding string represent the tasks
     */
    public List<String> printTasks() {
        List<String> output = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            output.add((i + 1) + "." + database.get(i));
        }
        return output;
    }

    public List<String> findTasks(String query) {
        query = query.trim();
        List<String> output = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getDescription().contains(query)) {
                output.add(count + "." + database.get(i));
                count++;
            }
        }
        return output;
    }
}
