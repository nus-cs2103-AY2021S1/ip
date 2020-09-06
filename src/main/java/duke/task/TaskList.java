package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.exception.InvalidArgumentException;
import duke.misc.Checker;
import duke.misc.Parser;
import duke.misc.Storage;

public class TaskList {
    private List<Task> database = new ArrayList<>();

    /**
     * Initializes the list of task with the information from the disk.
     *
     * @throws InvalidArgumentException
     */
    public TaskList() throws InvalidArgumentException {
        List<List<String>> data = Storage.readFile();
        for (List<String> tokens : data) {
            addTask(makeTask(tokens));
        }
    }

    public Task makeTask(List<String> tokens) throws InvalidArgumentException {
        LocalDateTime datetime;

        String taskType = tokens.get(0);
        String taskTitle = tokens.get(1).trim();
        String datetimeString = tokens.get(2).trim();
        String taskStatus = tokens.get(3);
        boolean isDone = taskStatus.equals("1");


        switch (taskType) {
        case "todo":
            return new Todo(taskTitle, isDone);
        case "deadline":
            Checker.checkTime(datetimeString);
            datetime = Parser.stringToTime(datetimeString);
            return new Deadline(taskTitle, datetime, isDone);
        case "event":
            Checker.checkTime(datetimeString);
            datetime = Parser.stringToTime(datetimeString);
            return new Event(taskTitle, datetime, isDone);
        default:
            throw new Error("An unexpected error has occurred");
        }
    }

    /**
     * Create a new task corresponding to the tokens then add it into the list of task, or throw an exception
     * if the input tokens is invalid.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        database.add(task);
    }

    /**
     * Gets the task from the list with the given index, or throw an exception if the index is invalid.
     *
     * @param index the query index
     * @return the corresponding task
     * @throws InvalidArgumentException
     */
    public Task getTask(int index) throws InvalidArgumentException {
        Checker.checkListIndex(index, database, "Out of range index for getting task.");
        return database.get(index - 1);
    }

    /**
     * Marks the task from the list with the given index as done, or throw an exception if the index is invalid.
     *
     * @param index the query index
     * @throws InvalidArgumentException
     */
    public void finishTask(int index) throws InvalidArgumentException {
        Checker.checkListIndex(index, database,"Out of range argument for DONE command.");
        database.get(index - 1).markAsDone();
    }

    /**
     * Removes the task from the list with the given index, or throw an exception if the index is invalid.
     *
     * @param index the query index
     * @return the task that has just been deleted
     * @throws InvalidArgumentException
     */
    public Task removeTask(int index) throws InvalidArgumentException {
        Checker.checkListIndex(index, database,"Out of range argument for DELETE command.");
        return database.remove(index - 1);
    }

    /**
     * Saves the list of task into the disk.
     */
    public void save() {
        Storage.writeFile(database);
    }

    /**
     * Counts the number of current tasks in the list.
     *
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
     *
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
        for (Task task : database) {
            if (task.getDescription().contains(query)) {
                output.add(count + "." + task);
                count++;
            }
        }
        return output;
    }
}
