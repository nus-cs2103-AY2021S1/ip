package duke.storage;

import duke.Duke;

import java.io.IOException;

public class DukeStorage {

    private Duke duke;
    private TaskStorage taskStorage;

    public DukeStorage(Duke duke) {
        this.duke = duke;
        taskStorage = duke.taskStorage;
    }

    public void loadSavedTasks() {
        duke.taskList.setTasks(taskStorage.getSavedTasks());
    }

    public void saveCurrentTasks() throws IOException {
        taskStorage.saveTasks(duke.taskList.getTasks());
    }
}
