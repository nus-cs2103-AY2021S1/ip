import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private Path dukeFile;

    public Storage(String filePath) {
        createFile(filePath);
    }

    private void createFile(String filePath) {
        try {

            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }

            Files.deleteIfExists(Paths.get(filePath + "/duke.txt"));
            dukeFile = Files.createFile(Paths.get(filePath + "/duke.txt"));

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void saveTasks(ArrayList<Task> taskList) {

        StringBuilder taskString = new StringBuilder();

        int i = 1;
        for (Task task: taskList) {
            taskString.append(String.format("%d. ", i));
            taskString.append(task.toString());
            taskString.append("\n");
            i++;
        }

        try {
            Files.writeString(dukeFile, taskString);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
