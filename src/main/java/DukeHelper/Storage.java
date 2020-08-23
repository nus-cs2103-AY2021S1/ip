package DukeHelper;
import Storage.LoadData;
import Storage.SaveData;
import Task.Task;

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
