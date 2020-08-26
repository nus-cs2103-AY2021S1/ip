import java.io.File;
import java.io.IOException;

public class Storage {
    public static TaskList loadFromMem() throws DukeException {
        File saveFile = new File("./src/main/data/duke.txt");
        if (saveFile.exists()) {
            convertToTaskList(saveFile);
        } else {
            throw new DukeNoSaveFileFoundException();
        }
        return null;
    }

    public static void makeDirectory() throws DukeException {
        new File("./src/main/data").mkdirs();
        try {
            new File("./src/main/data/duke.txt").createNewFile();
        } catch (IOException e) {
            throw new DukeException();
        }
    }

    public static void writeToMem() {

    }

    private static void convertToTaskList(File saveFile) {

    }

    private static void convertToSaveFile(TaskList taskList) {

    }

}
