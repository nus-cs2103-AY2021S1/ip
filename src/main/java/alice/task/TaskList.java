package alice.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import alice.AliceException;
import alice.command.InvalidCommandException;

/**
 * Represent the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates a new TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new TaskList from previously saved tasks.
     *
     * @param encodedTasks list of string of saved tasks from the previous session.
     * @throws AliceException if the saved files are corrupted.
     */
    public TaskList(List<String> encodedTasks) throws AliceException {
        this.tasks = new ArrayList<>();
        if (encodedTasks != null) {
            for (int i = 0; i < encodedTasks.size(); i++) {
                String currTask = encodedTasks.get(i);
                String[] typeAndDetails = currTask.split(" \\| ", 2);

                if (typeAndDetails.length != 2) {
                    throw new AliceException("Corrupted data");
                }

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
                    throw new AliceException("Corrupted data");
                }
            }
        }
    }

    /**
     * Encodes the list of tasks into a list of strings for saving.
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
     * Gets the string representation of all the tasks.
     *
     * @return the string representation of all tasks, or null if there are no tasks.
     */
    public String getAllTasks() {
        if (tasks.isEmpty()) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            s.append(i + 1).append(". ").append(tasks.get(i));
            if (i < tasks.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    /**
     * Searches for all tasks that matches any of the keywords.
     *
     * @param keywords the list of keywords to search against.
     * @return the string representation of all tasks that matches.
     */
    public String find(String... keywords) {
        StringBuilder s = new StringBuilder();

        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).containKeywords(keywords)) {
                s.append(++counter).append(". ").append(tasks.get(i)).append("\n");
            }
        }

        if (counter == 0) {
            return null;
        } else {
            s.setLength(s.length() - 1);
            return s.toString();
        }
    }

    /**
     * Gets the number of tasks in TaskList.
     *
     * @return number of tasks.
     */
    public int getNumberOfTasks() {
        return tasks.size();
    }

    /**
     * Adds the task to TaskList.
     *
     * @param t the task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Marks a task as done.
     *
     * @param index the index indicating the task to be marked as done.
     * @return the completed task.
     * @throws InvalidCommandException if the index does not point to a task.
     */
    public Task markTaskAsDone(int index) throws InvalidCommandException {
        try {
            tasks.get(index).markAsDone();
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("That task number does not exist.");
        }
    }

    /**
     * Deletes a task.
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
     * Clears all tasks.
     */
    public void clearAllTasks() {
        tasks.clear();
    }

}
