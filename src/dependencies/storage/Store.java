package dependencies.storage;

import dependencies.task.Task;


public class Store {
    /** todoList that stores the tasks. */
    private final Task[] todoList;
    private int todoIdx = 0;

    /** Private constructor */
    private Store(int c) {
        todoList = new Task[c];
    }

    /**
     * Initializer for the Store object.
     *
     * @param storageCapacity
     * @return the Store object
     */
    public static Store initStorage(int storageCapacity) {
        return new Store(storageCapacity);
    }

    /**
     * Returns a String in the form of a list with \n appended
     * at the end of each item.
     *
     * @return todoList in a form of a String
     */
    public String getTodosInList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < todoIdx; i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(todoList[i].toString());
            if (i != todoIdx - 1) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    /**
     * Adds the specified task to the todoList.
     *
     * @param task
     * @return a string represenitng the newly added task
     */
    public String add(Task task) {
        todoList[todoIdx++] = task;
        return task.toString();
    }

    /**
     * Finds the given task at index and completes it.
     *
     * @param nums am array of numbers in string form
     * @return a string represenitng the newly completed task.
     */
    public String done(Integer[] nums) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            Task t = todoList[nums[i] - 1];
            t.completed();
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }

    public String deleteTask(Integer i) {
        return null;
    }




}
