package Duke;

import java.util.ArrayList;

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
    public Task remove(int order) {
        return dukeList.remove(order - 1);
    }

    /**
     * Marks a task as done in the Task List.
     *
     * @param order the order to mark as done.
     * @return the task that was marked done.
     */
    public Task markDone(int order) {
        Task task = dukeList.get(order - 1);
        task.setDone();
        return task;
    }

    /**
     * Creates a string of the list of tasks.
     *
     * @return the string of each task.
     */
    public String listToString() {
        String output = "";
        for (int i = 0; i < dukeList.size(); i++) {
            output = output + (i + 1) + ". " + dukeList.get(i).toString() + "\n";
        }

        return output;
    }

    /**
     * returns the number of tasks in the Task List.
     *
     * @return number of tasks in the Task List.
     */
    public int getSize() {
        return dukeList.size();
    }
}