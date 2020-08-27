package duke.data;

import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;

public class DukeTaskListSideEffects {

    public boolean getTask;
    public boolean addTask;
    public boolean deleteTask;
    public boolean getSize;
    public boolean setTasks;
    public boolean getTasks;

    private DukeTaskListSideEffects() {
        getTask = false;
        addTask = false;
        deleteTask = false;
        getSize = false;
        setTasks = false;
        getTasks = false;
    }

    private static DukeTaskListSideEffects instance;
    public static DukeTaskListSideEffects getInstance() {
        if (instance == null) {
            instance = new DukeTaskListSideEffects();
        }

        return instance;
    }

    public void reset() {
        getTask = false;
        addTask = false;
        deleteTask = false;
        getSize = false;
        setTasks = false;
        getTasks = false;
    }
}
