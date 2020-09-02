package duke.storage;

import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;


public class TaskStorageStub extends TaskStorage {

    private TaskStorageSideEffects sideEffects = TaskStorageSideEffects.getInstance();

    public TaskStorageStub() throws IOException {

    }

    @Override
    public ArrayList<Task> getSavedTasks() {
        sideEffects.getSavedTasks = true;
        return null;
    }

    @Override
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        sideEffects.saveTasks = true;
    }
}
