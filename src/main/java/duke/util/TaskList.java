package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the list of task for {@code Duke}.
 */
public class TaskList {

    /** The list of task added by the user. */
    private List<Task> taskList;

    /**
     * Constructs an empty task list which utilised an {@code ArrayList}.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a task list given a list of Strings for task input.
     *
     * @param input List of inputs.
     */
    public TaskList(List<String> input) {
        taskList = new ArrayList<>();
        for (String s: input) {
            taskList.add(Util.convertStringToTask(s));
        }
    }

    /**
     * Exports all the task within the task list into a list of Strings.
     *
     * @return List of task outputs.
     */
    public List<String> export() {
        return taskList
                .stream()
                .map(x -> x.getSaveToFileString())
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return The number of elements in this list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param i index of the element to return
     * @return The element at the specified position in this list.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @param t element to be appended to this list
     * @return The status of adding to list is successful.
     */
    public boolean add(Task t) {
        return taskList.add(t);
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param idx The index of the element to be removed.
     * @return The element that was removed from the list.
     */
    public Task remove(int idx) {
        return taskList.remove(idx);
    }

}
