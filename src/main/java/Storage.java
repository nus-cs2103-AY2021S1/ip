import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage takes in a file path and read the file to turn it into arraylist of tasks
 * It also saves arraylist of Tasks into a file.
 */
public class Storage {

    private String filePath;

    /**
     * Storage constructor specifying a file path.
     * @param filePath
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read the given file path and parse each line and added them into ArrayList of Task.
     *
     * @param filepath
     * @return ArrayList of Task
     * @throws FileNotFoundException
     */
    public static ArrayList<Task> readFromFile(String filepath) throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filepath);
        Scanner s = new Scanner(f, "UTF-8");

        while (s.hasNext()) {
            String input = s.nextLine();
            tasks.add(StorageParser.parse(input));
        }
        s.close();
        return tasks;
    }

    /**
     * Start loading storage to read file.
     * @return ArrayList of Task
     * @throws FileNotFoundException
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = readFromFile(filePath);
        return tasks;
    }

    /**
     * Save the arraylist of Tasks into a file.
     *
     * @param taskArr
     * @throws IOException
     */
    public void saveToFile(ArrayList<Task> taskArr) throws IOException {
        assert (!filePath.isEmpty());
        File oldFile = new File(filePath);
        oldFile.delete();
        File newFile = new File(filePath);
        FileWriter fileWriter = new FileWriter(newFile, StandardCharsets.UTF_8, false);
        for (Task task : taskArr) {
            fileWriter.write(task.toString());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
