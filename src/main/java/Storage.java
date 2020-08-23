import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving of tasks.
 */
public class Storage {

    private final File file;
    private final String filePath;

    /**
     * Creates a storage.
     * @param filePath Directory path of file.
     */
    Storage(String filePath) throws IOException {
        File tempFile = new File(filePath);

        // If file does not exist, creates file in directory
        if (!tempFile.exists()) {
                tempFile.createNewFile();
        }
        this.file = tempFile;
        this.filePath = filePath;
    }

    /**
     * Converts file into a list of task.
     * @return List containing the task.
     * @throws FileNotFoundException If file does not exist.
     */
    public ArrayList<Task> getList() throws FileNotFoundException, BobInvalidDateAndTimeException {
        Scanner scanner = new Scanner(this.file);
        ArrayList<Task> list = new ArrayList<>();

        // Format of task in file is "D/0/return book/June 6th"
        // where "0" means undone while "1" means done
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("/");
            if (split[0].equals("T")) {
                String description = split[2];
                Task task = split[1].equals("0")
                        ? new Task(description)
                        : new Task(description).markDone();
                list.add(task);
            } else if (split[0].equals("D")) {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Deadline deadline = split[1].equals("0")
                        ? new Deadline(description, date, time)
                        : new Deadline(description, date, time).markDone();
                list.add(deadline);
            } else if (split[0].equals("E")) {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Event event = split[1].equals("0")
                        ? new Event(description, date, time)
                        : new Event(description, date, time).markDone();
                list.add(event);
            }
        }
        scanner.close();
        return list;
    }

    /**
     * Updates the file data based on the list.
     * @param list Bob's task list.
     * @return A new storage with the updated file.
     * @throws IOException If error occurs while writing the file.
     */
    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        String textToAdd = "";
        for (Task task : list) {
            textToAdd += task.convertToStringData() + System.lineSeparator();
        }

        fw.write(textToAdd);
        fw.close();
    }
}
