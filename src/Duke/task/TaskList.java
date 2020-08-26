package Duke.task;

import java.util.ArrayList;

/**
 * This class is to contain tasks.
 * @param <T>
 */
public class TaskList<T> {
    private ArrayList<T> taskList;

    /**
     * Initialize a TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * This is to add new tasks into
     * the TaskList.
     * @param event new task to be added.
     */
    public void addMemory(T event) {
        this.taskList.add(event);
    }

    /**
     * This is to get all Tasks in the list.
     * @return
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
