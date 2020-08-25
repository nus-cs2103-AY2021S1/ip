import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Encapsulates the handling of Duke's save file.
 */
public class DukeSaveFile {

    private static final File DIRECTORY = new File("../data");
    private static final File TEXT_FILE = new File("../data/duke.txt");

    /**
     * Checks if duke.txt file exists in [project_root]/data.
     * If it exists, load the data into Duke.
     * If it does not exist, create one.
     */
    public static void checkSaveFile() {
        if (DIRECTORY.exists()) {
            if (TEXT_FILE.exists()) {
                readTextFile();
            } else {
                createTextFile();
            }
        } else {
            createDataDirectory();
            createTextFile();
        }
    }

    /**
     * Creates data directory in the project root folder.
     */
    private static void createDataDirectory() {
        boolean hasCreatedDir = DIRECTORY.mkdir();
        if (hasCreatedDir) {
            System.out.println("A data directory has been successfully created at the root folder.");
        } else {
            System.out.println("A data directory cannot be created. Please try again.");
        }
    }

    /**
     * Creates duke.txt in [project_root]/data.
     */
    private static void createTextFile() {
        String failMessage = "A text file cannot be created. Please try again.";
        try {
            boolean hasCreatedFile = TEXT_FILE.createNewFile();
            if (hasCreatedFile) {
                System.out.println("A text file has been successfully created at [root_folder]/data.");
            } else {
                System.out.println(failMessage);
            }
        } catch (IOException e) {
            System.out.println(failMessage);
        }
    }

    /**
     * Reads duke.txt and adds tasks into Duke's tasks list.
     */
    private static void readTextFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(TEXT_FILE));
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(" / ");
                Task task;
                boolean isDone = Integer.parseInt(arr[1]) != 0;
                if (arr[0].equals("T")) {    // Todo
                    task = new Todo(arr[2], isDone);
                } else if (arr[0].equals("E")) {    // Event
                    task = new Event(arr[2], isDone, arr[3]);
                } else {    // Deadline
                    task = new Deadline(arr[2], isDone, arr[3]);
                }
                Duke.addTask(task);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("The text file cannot be read. Please try again.");
        }
    }

    /**
     * Writes tasks' data to duke.txt
     *
     * @param tasks List of tasks to be saved.
     */
    public static void writeToSaveFile(ArrayList<Task> tasks) {
        StringBuilder data = new StringBuilder();
        for (Task task: tasks) {
            data.append(task.getData()).append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE));
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to write data to duke.txt. Please try again.");
        }
    }
}
