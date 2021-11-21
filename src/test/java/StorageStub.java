import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

public class StorageStub extends Storage {

    ArrayList<Task> tempTaskListStorage = new ArrayList<>();

    /**
     * Public Constructor.
     *
     * @param filepath         Path of data storage file.
     * @param aliasMapFilePath Path of key mappings file.
     */
    public StorageStub(Path filepath, Path aliasMapFilePath) {
        super(filepath, aliasMapFilePath);
    }

    @Override void saveTaskList(ArrayList<Task> tasks) {
        tempTaskListStorage = tasks;
    }

    @Override ArrayList<Task> loadTaskList() {
        return tempTaskListStorage;
    }

    @Override public HashMap<String, String> loadAliasMapping() {
        return new HashMap<>();
    }

    @Override public void saveMapping(HashMap<String, String> aliasMap) {

    }
}
