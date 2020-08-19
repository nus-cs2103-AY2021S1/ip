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
            if (i == todoList.size() - 1) {
                sb.append("So stop procrastinating!");
            }
        }
        if (todoList.size() == 0) {
            sb.append("Theres's nothing here!\n")
                    .append("Try adding something to your list?");
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
     * Finds all given task specified and deletes it from
     * list.
     *
     * @param nums
     * @return
     */
    public String deleteTask(Integer nums) {
        Task t = todoList.get(nums - 1);
        todoList.remove(nums - 1);
        return t.toString();
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

    /**
     * Returns the number of incomplete tasks in the list.
     *
     * @return number of incomplete tasks
     */
    public int getNumOfIncompleted() {
        int c = 0;
        for (int i = 0; i < todoList.size(); i++) {
            if (!todoList.get(i).isCompleted()) {
                c++;
            }
        }
        return c;
    }

}
