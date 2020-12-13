package duke.storage;

import java.util.ArrayList;

import duke.task.Task;

public class State {
    private final ArrayList<Task> listOfTasks;

    public State(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns the list of tasks from the state
     *
     * @return A list of tasks from the state
     */
    public ArrayList<Task> getListOfTasks() {
        return listOfTasks;
    }
}
