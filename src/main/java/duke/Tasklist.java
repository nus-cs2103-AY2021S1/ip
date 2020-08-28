package duke;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


/**
 * A Tasklist to handle operations pertaining to the Tasklist.
 */
public class Tasklist {

    private List<Task> taskList;
    private Storage storage;

    /**
     * Constructor for the tasklist.
     *
     * @param storage Takes in the storage object for initialisation.
     */
    public Tasklist(Storage storage) {
        this.storage = storage;
    }

    public void clearList() {
        taskList = new ArrayList<>();
    }

    /**
     * Method to begin loading the list through the storage.
     *
     * @throws IOException When there is no pre-loaded file to load.
     */
    public void loadList() throws IOException {
        this.taskList = storage.load();
    }

    /**
     * Method to save the current Tasklist into the local storage.
     *
     * @throws IOException When there is no pre-loaded file to write.
     */
    public void updateStorage() throws IOException {
        this.storage.writeData(this.taskList);
    }

    /**
     * To add new tasks to the Tasklist.
     *
     * @param newTask Task of which to be added.
     */
    public void addTask(Task newTask) {
        this.taskList.add(newTask);
    }

    /**
     * To modify a tasking's completion status.
     *
     * @param index Position of the task in the list.
     */
    public void makeTaskDone(int index) {
        this.taskList.get(index).makeDone();
    }

    /**
     * To remove a tasking from the list.
     *
     * @param index Index of tasking desired for removal.
     */
    public void removeTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * To retrieve the details of a specific task.
     *
     * @param index Index of the desired task.
     * @return Returns the task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * To retrieve the current count of tasks in a list.
     *
     * @return total count of the tasks.
     */
    public int getTaskSize() {
        return this.taskList.size();
    }
}

