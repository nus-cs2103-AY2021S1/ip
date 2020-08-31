package Duke.task;

import java.util.ArrayList;

/**
 * This class is to contain tasks.
 * @param <T> The data type of the object in the task list.
 */
public class TaskList<T> {
    private ArrayList<T> taskList;

    /**
     * Initializes a TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds new tasks into
     * the TaskList.
     *
     * @param event new task to be added.
     */
    public void addMemory(T event) {
        this.taskList.add(event);
    }

    /**
     * Gets all Tasks in the list.
     * @return The arrayList which stores all the tasks.
     */
    public ArrayList<T> getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String results = "";
        int size = this.taskList.size();
        if (size != 0) {
            for (int i = 1; i < size; i++) {
                results += "    " + i + ". " + taskList.get(i - 1) + "\n";
            }
            results += "    " + size + ". " + taskList.get(size - 1);
        }
        return results;
    }
}
