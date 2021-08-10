package duke.logic;

import java.util.ArrayList;
import java.util.stream.Collectors;

import duke.logic.tasks.Task;

/**
 * Represents the list of tasks the user has and keeps track of the current state of the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Instantiates a TaskList object with a list of tasks.
     *
     * @param tasks Arraylist of tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Instantiates a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Returns the task with the specified task number.
     *
     * @param taskNum The task number to be retrieved.
     * @return Task at that task number.
     */
    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    /**
     * Adds the specified task into the list of tasks.
     *
     * @param t The task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Deletes the task with the specified task number.
     *
     * @param taskNum The task number to be deleted.
     */
    public void deleteTask(int taskNum) {
        this.tasks.remove(taskNum - 1);
    }

    /**
     * Prints the list of tasks in a numbered format.
     */
    public String printList() {
        assert tasks.size() > 0
                : "List of tasks is not supposed to be empty";

        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            list += num + ". " + tasks.get(i) + "\n";
        }

        return list;
    }

    /**
     * Finds the matching tasks in the list according to the specified keyword.
     *
     * @param keyword The keyword to find tasks.
     * @return TaskList containing tasks with the same keyword.
     */
    public TaskList findTasks(String keyword) {
        assert keyword != null
                : "Keyword used to find tasks is not supposed to be null";

        ArrayList<Task> foundTasks = new ArrayList<>();

        this.tasks.stream()
                .filter(t -> t.toString().contains(keyword))
                .collect(Collectors.toCollection(() -> foundTasks));

        return new TaskList(foundTasks);
    }
}
