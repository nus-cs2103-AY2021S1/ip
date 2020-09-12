package alice.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import alice.AliceException;
import alice.command.InvalidCommandException;

/**
 * Represent the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Create a new TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Create a new TaskList from previously saved tasks.
     *
     * @param encodedTasks list of string of saved tasks from the previous session, if any.
     * @param logger       the consumer that takes in a String to be logged in the StorageFile
     *                     if the file is corrupted.
     */
    public TaskList(List<String> encodedTasks, Consumer<String> logger) {
        tasks = new ArrayList<>();
        for (int i = 0; i < encodedTasks.size(); i++) {
            String currTask = encodedTasks.get(i);
            String[] typeAndDetails = currTask.split(" \\| ", 2);

            if (typeAndDetails.length != 2) {
                logger.accept("Partially corrupted data");
                continue;
            }

            try {
                switch (typeAndDetails[0]) {
                case "T":
                    tasks.add(Todo.decode(typeAndDetails[1]));
                    break;
                case "D":
                    tasks.add(Deadline.decode(typeAndDetails[1]));
                    break;
                case "E":
                    tasks.add(Event.decode(typeAndDetails[1]));
                    break;
                default:
                    logger.accept("Partially corrupted data");
                    break;
                }
            } catch (AliceException ex) {
                // corrupted line, skip the line and continue.
                logger.accept("Partially corrupted data");
            }
        }
    }

    /**
     * Encode the list of tasks into a list of strings for saving.
     * The tasks are encoded in a form that ALICE can understand.
     *
     * @return the list of string representation of the encoded tasks.
     */
    public List<String> encode() {
        String[] dataToSave = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            dataToSave[i] = tasks.get(i).encode();
        }
        return Arrays.asList(dataToSave);
    }

    /**
     * Get the string representation of all the tasks.
     *
     * @return the string representation of all tasks, or null if there are no tasks.
     */
    public String getAllTasks() {
        if (tasks.isEmpty()) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        s.setLength(s.length() - 1);
        return s.toString();
    }

    /**
     * Search for all tasks that matches any of the keywords.
     *
     * @param keywords the list of keywords to search against.
     * @return the string representation of all tasks that matches.
     */
    public String find(String... keywords) {
        assert keywords.length != 0 : "Keywords used for find tasks cannot be empty";
        StringBuilder s = new StringBuilder();

        boolean hasZeroResults = true;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containKeywords(keywords)) {
                s.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                hasZeroResults = false;
            }
        }

        if (hasZeroResults) {
            return null;
        }

        s.setLength(s.length() - 1);
        return s.toString();
    }

    /**
     * Get the number of tasks in TaskList.
     *
     * @return number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Add the task to TaskList.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        assert !t.getDescription().isBlank() : "Cannot add a task with empty description";
        tasks.add(t);
    }

    /**
     * Mark a task as done.
     *
     * @param index the index indicating the task to be marked as done.
     * @return the completed task.
     * @throws InvalidCommandException if the index does not point to a task.
     */
    public Task markTaskAsDone(int index) throws InvalidCommandException {
        try {
            boolean isOpSuccessful = tasks.get(index).markAsDone();
            if (!isOpSuccessful) {
                return null;
            }
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("That task number does not exist.");
        }
    }

    /**
     * Delete a task.
     *
     * @param index the index indicating the task to be deleted.
     * @return the deleted task.
     * @throws InvalidCommandException if the index does not point to a task.
     */
    public Task deleteTask(int index) throws InvalidCommandException {
        try {
            return tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("That task number does not exist.");
        }
    }

    /**
     * Clear all tasks.
     */
    public void clearAllTasks() {
        tasks.clear();
    }

    /**
     * Clear only completed tasks.
     */
    public void clearCompletedTasks() {
        tasks.removeIf(Task::isCompleted);
    }
}
