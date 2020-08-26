package Duke;

import java.io.IOException;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    TaskList load() throws IOException, ClassNotFoundException {
        return Reader.readListFromFile(this.filePath);
    }

    void save(TaskList taskList) {
        Writer.writeListToFile(taskList, filePath);
    }

    boolean doesExist() {
        return Reader.doesFileExist(this.filePath);
    }

}
