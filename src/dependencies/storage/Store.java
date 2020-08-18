package dependencies.storage;

import dependencies.task.Task;

import java.util.ArrayList;


public class Store {
    /** todoList that stores the tasks. */
    private final ArrayList<Task> todoList;

    /** Private constructor */
    private Store() {
        todoList = new ArrayList<>();
    }

    /**
     * Initializer for the Store object.
     *
     * @return the Store object
     */
    public static Store initStorage() {
        return new Store();
    }

    /**
     * Returns a String in the form of a list with \n appended
     * at the end of each item.
     *
     * @return todoList in a form of a String
     */
    public String getTodosInList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < todoList.size(); i++) {
            sb.append(i+1);
            sb.append(". ");
            sb.append(todoList.get(i).toString());
            if (i != todoList.size() - 1) {
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
        todoList.add(task);
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
            Task t = todoList.get(nums[i] - 1);
            t.completed();
            if (i != nums.length - 1) {
                sb.append(t.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Returns the number of tasks in the todoList. Includes completed task.
     *
     * @return size of the list as a String
     */
    public int getListSize() {
        return todoList.size();
    }

    /**
     * Returns the number of completed tasks in the list.
     *
     * @return number of completed tasks
     */
    public int getNumOfCompleted() {
        int c = 0;
        for (int i = 0; i < todoList.size(); i++) {
            if (todoList.get(i).isCompleted()) {
                c++;
            }
        }
        return c;
    }

    public String deleteTask(Integer i) {
        return null;
    }




}
