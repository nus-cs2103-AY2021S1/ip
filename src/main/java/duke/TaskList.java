package duke;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import duke.task.Task;

/**
 * The TaskList class contains the task list and operations on the list.
 */
public class TaskList {
    private List<Task> list;

    /**
     * Creates and initialises a task list with the given list.
     *
     * @param list List of tasks to be added.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /** Returns string representation all the tasks in the task list. */
    public String list() {
        String result = "Here are the tasks in your list:\n";
        result += listToString(list);
        return result;
    }

    /**
     * Returns the size of the list.
     *
     * @return Size of list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task at the specified position.
     *
     * @param taskNumber The position of the task in the list.
     * @return The task at that position.
     */
    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    /**
     * Adds the task to the list.
     *
     * @param task Task to be added to list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes the task with the specified position in the list and return
     * the task that is removed.
     *
     * @param taskNumber The position of the task to be deleted.
     * @return The task that is deleted.
     */
    public Task removeTask(int taskNumber) {
        return list.remove(taskNumber - 1);
    }

    /**
     * Returns string representation of tasks with the specified keyword.
     *
     * @param keyword Keyword to be searched.
     */
    public String find(String keyword) {
        List<Task> filteredList = list.stream()
                .filter(t -> t.getDetails().contains(keyword))
                .collect(Collectors.toList());
        String result = "Here are the matching tasks in your list:\n";
        result += listToString(filteredList);
        return result;
    }

    private String listToString(List<Task> list) {
        Iterator<Task> iterator = list.iterator();
        int count = 0;
        String result = "";
        while (iterator.hasNext()) {
            count++;
            result += count + ". " + iterator.next().toString() + "\n";
        }
        return result;
    }
}
