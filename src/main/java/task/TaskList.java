package task;

import java.util.ArrayList;

import exception.DukeException;
import magic.Format;
import magic.MyString;

public class TaskList {

    private final ArrayList<Task> dukeList = new ArrayList<>();

    /**
     * Constructor initialises TaskList with a list of tasks from the memory.
     *
     * @param list list of tasks to load into the Task List.
     */
    public TaskList(ArrayList<Task> list) {
        this.dukeList.addAll(list);
    }

    /**
     * Method to add a task to Task List
     *
     * @param task Task to add to the list.
     */
    public void add(Task task) {
        // Add to List
        dukeList.add(task);
    }

    /**
     * Removes a task from the Task List
     *
     * @param order the order to remove from the tasklist
     * @return the task that was removed.
     */
    public Task remove(int order) throws DukeException {
        try {
            return dukeList.remove(order - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MyString.ERROR_INVALID_INDEX);
        }
    }

    /**
     * Marks a task as done in the Task List.
     *
     * @param order the order to mark as done.
     * @return the task that was marked done.
     */
    public Task markDone(int order) throws DukeException {
        try {
            Task task = dukeList.get(order - 1);
            task.setDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MyString.ERROR_INVALID_INDEX);
        }

    }

    /**
     * Creates a string of the list of tasks.
     *
     * @return the string of each task.
     */
    public String listToString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < dukeList.size(); i++) {
            int order = i + 1;
            String taskString = dukeList.get(i).toString();
            String line = String.format(Format.DISPLAY_TASK_AS_LIST,
                    order, taskString);
            output.append(line);
        }

        return output.toString();
    }

    /**
     * returns the number of tasks in the Task List.
     *
     * @return number of tasks in the Task List.
     */
    public int getSize() {
        return dukeList.size();
    }

    /**
     * Return subList of tasks matching with subName
     * @param subName Name to compare.
     * @return TaskList of tasks whose names match subName
     */
    public TaskList find(String subName) {
        TaskList subList = new TaskList(new ArrayList<>());
        for (Task task : dukeList) {
            String name = task.getName();
            String s1 = name.toLowerCase();
            String s2 = subName.toLowerCase();
            if (s1.contains(s2)) {
                subList.add(task);
            }
        }

        return subList;
    }

    public Task setTag(int order, String tagName) throws DukeException {
        assert !tagName.isEmpty() : MyString.ERROR_INVALID_DESC;
        try {
            Task task = dukeList.get(order - 1);
            task.setTag(tagName);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(MyString.ERROR_INVALID_INDEX);
        }
    }
}
