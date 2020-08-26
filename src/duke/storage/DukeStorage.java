package duke.storage;

import duke.data.DukeTaskList;

import java.io.IOException;

public class DukeStorage {

    public static void loadSavedTasks() throws IOException {
        DukeTaskList.tasks = TaskStorage.getInstance().getSavedTasks();
    }

    public static void saveCurrentTasks() throws IOException {
        TaskStorage.getInstance().saveTasks(DukeTaskList.tasks);
    }
}
