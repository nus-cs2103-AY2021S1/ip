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
        ArrayList<Task> tasksList = tm.getTasksList();

        for (int i = 0; i < tasksList.size(); i++) {

            if (tasksList.get(i) instanceof Todo) {
                writer.write("T ## " + (tasksList.get(i).getDone() ? 1 : 0) + " ## "
                        + tasksList.get(i).getDescription() + "\n");
            }

            if (tasksList.get(i) instanceof Deadline) {
                writer.write("D ## " + (tasksList.get(i).getDone() ? 1 : 0) + " ## "
                        + ((Deadline) tasksList.get(i)).getDescription() + " ## "
                        + ((Deadline) tasksList.get(i)).getDate() + "\n");
            }

            if (tasksList.get(i) instanceof Event) {
                writer.write("E ## " + (tasksList.get(i).getDone() ? 1 : 0) + " ## "
                        + ((Event) tasksList.get(i)).getDescription() + " ## "
                        + ((Event) tasksList.get(i)).getDate() + " " + ((Event) tasksList.get(i)).getTime() + "\n");
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