package duke.io;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Tasklist class define rules the list of tasks.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class TaskList {
    private final ArrayList<Task> taskArrayList;

    /**
     * Class constructor.
     * Initialises taskArraylist will be empty.
     */
    public TaskList() {
        this.taskArrayList = new ArrayList<>();
    }

    /**
     * Overloaded method.
     * Class constructor.
     * Initialises taskArrayList with data from filepath.
     *
     * @param taskArrayList task array list from file.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Returns Arraylist that stores all the task.
     *
     * @return arraylist of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * Adds task into the taskArrayList.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        this.taskArrayList.add(task);
    }

    /**
     * Returns number of task in list.
     *
     * @return size of arraylist.
     */
    public int sizeOfList() {
        return this.taskArrayList.size();
    }

    /**
     * Returns task by index.
     *
     * @param index int number to retrieve task.
     * @return task based on the index.
     */
    public Task retrieveTask(int index) {
        return this.taskArrayList.get(index);
    }

    /**
     * Deletes task by index.
     *
     * @param index task index that is to be deleted.
     */
    public void deleteTask(int index) {
        this.taskArrayList.remove(index);
    }

    /**
     * Searches input string if constains in task description.
     *
     * @param toFind search value.
     * @return arraylist of search result.
     */
    public ArrayList<Task> find(String toFind) {
        ArrayList<Task> searchResults = new ArrayList<>();
        for (Task task : taskArrayList) {
            if (task.getDescription().toLowerCase().contains(toFind.toLowerCase())) {
                searchResults.add(task);
            }
        }
        return searchResults;
    }
}
