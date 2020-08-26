package duke.storage;

import duke.Duke;

import java.io.IOException;

public class DukeStorage {

    private Duke duke;

    public DukeStorage(Duke duke) {
        this.duke = duke;
    }

    public void loadSavedTasks() throws IOException {
        duke.taskList.tasks = TaskStorage.getInstance().getSavedTasks();
    }

    public void saveCurrentTasks() throws IOException {
        TaskStorage.getInstance().saveTasks(duke.taskList.tasks);
    }
}
