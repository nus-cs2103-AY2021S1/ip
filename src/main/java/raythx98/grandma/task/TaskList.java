package raythx98.grandma.task;

import java.util.ArrayList;
import java.util.List;

import raythx98.grandma.exception.InvalidIndexException;

/**
 * Represents tasks.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    /**
     * Removes a task from List of tasks based on the index.
     *
     * @param index Index of task to be removed.
     * @return String which represents the task removed.
     */
    public String removeTask (int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        return task.toString();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a string representation of the list of all tasks that match the key word.
     *
     * @return String representation of the list of all tasks that match the key word
     */
    public String findTask(String keyWord) throws InvalidIndexException {
        TaskList matchedTasks = new TaskList();
        for (Task i: tasks) {
            String taskDescription = i.getDescription().toLowerCase();
            String lowerCaseTaskDescription = keyWord.toLowerCase();
            if (taskDescription.contains(lowerCaseTaskDescription)) {
                matchedTasks.addTask(i);
            }
        }
        if (matchedTasks.getSize() == 0) {
            throw new InvalidIndexException();
        }
        return matchedTasks.toString();
    }

    /**
     * Returns a string representation of the tasks.
     *
     * @return String representation of the tasks.
     */
    public String toString() {
        String task = "";
        if (getSize() >= 1) {
            task = "    1. " + tasks.get(0).toString();
            for (int i = 2; i <= getSize(); i++) {
                task = task + "\n    " + i + ". " + tasks.get(i - 1).toString();
            }
        } else {
            task = "empty";
        }
        return task;
    }
}
