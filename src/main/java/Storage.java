import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Handles txt files created.
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes tasks to the .txt file to store the data.
     * @param taskManager task manager that contains the list of tasks.
     * @throws IOException
     */
    public void writeToFile(TaskManager taskManager) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        ArrayList<Task> tasksList = taskManager.getTaskList();

        for (Task task : tasksList) {

            if (task instanceof Todo) {
                fileWriter.write("T ## " + (task.getDone() ? 1 : 0) + " ## "
                        + (task.getPriority()) + " ## " + task.getDescription() + "\n");
            }

            if (task instanceof Deadline) {
                fileWriter.write("D ## " + (task.getDone() ? 1 : 0) + " ## "
                        + (task.getPriority()) + " ## " + task.getDescription() + " ## "
                        + ((Deadline) task).getDate() + "\n");
            }

            if (task instanceof Event) {
                fileWriter.write("E ## " + (task.getDone() ? 1 : 0) + " ## "
                        + (task.getPriority()) + " ## " + task.getDescription() + " ## "
                        + ((Event) task).getDate() + " " + ((Event) task).getTime() + "\n");
            }
        }
        fileWriter.close();
    }

    /**
     * Reads data stored in the file.
     * @param fileName file to be read.
     * @return list of lines read in the file.
     * @throws IOException
     */
    public static ArrayList<String> readSavedFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            return (ArrayList<String>) Files.readAllLines(Paths.get(fileName));
        } else {
            //file.createNewFile();
            return new ArrayList<>();
        }
    }
}