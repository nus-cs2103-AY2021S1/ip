package duke.dukehelper;
import duke.storage.LoadData;
import duke.storage.SaveData;
import duke.task.Task;

import java.util.ArrayList;

public class Storage {
    private String path;
    public Storage(String path) {
        this.path = path;
    }
    public ArrayList<String> loadSavedTasks() {
        return LoadData.getSavedTasks(path);
    }
    public void saveTasks(ArrayList<Task> data) {
        SaveData.saveData(path,data);
    }
}
