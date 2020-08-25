import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriting {

    public static void writeToFile(String filePath, List<Task> tasksToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        int totalTasks = tasksToWrite.size();

        for (int i = 0; i < totalTasks; i++) {
            Task writeTask = tasksToWrite.get(i);
            fw.write(writeTask.toString() + "\n");
        }
        fw.close();
    }
}