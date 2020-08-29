import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles txt files created.
 */
public class FileHandler {

    /**
     * Writes tasks to the .txt file to store the data.
     * @param file .txt file.
     * @param tm task manager that contains the list of tasks.
     * @throws IOException
     */
    public static void writeToFile(String file, TaskManager tm) throws IOException {

        FileWriter writer = new FileWriter(file);
        ArrayList<Task> taskList = tm.getTaskList();

        for (int i = 0; i < taskList.size(); i++) {

            if (taskList.get(i) instanceof Todo) {
                writer.write("T ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + taskList.get(i).getDescription() + "\n");
            }

            if (taskList.get(i) instanceof Deadline) {
                writer.write("D ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + ((Deadline) taskList.get(i)).getDescription() + " ## " + ((Deadline) taskList.get(i)).getDate() + "\n");
            }

            if (taskList.get(i) instanceof Event) {
                writer.write("E ## " + (taskList.get(i).getDone() ? 1 : 0) + " ## " + ((Event) taskList.get(i)).getDescription() + " ## " + ((Event) taskList.get(i)).getDate() + " " + ((Event) taskList.get(i)).getTime() + "\n");
            }
        }
        writer.close();
    }

    /**
     * Reads data stored in the file.
     * @param fileName file to be read.
     * @return list of lines read in the file.
     * @throws IOException
     */
    public static List<String> readSavedFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists()) {
            return Files.readAllLines(Paths.get(fileName));
        } else {
            return null;
        }
    }
}