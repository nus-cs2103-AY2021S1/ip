package bob.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bob.data.task.Deadline;
import bob.data.task.Event;
import bob.data.task.Task;
import bob.exceptions.BobInvalidDateAndTimeException;

/**
 * Handles loading and saving of tasks.
 */
public class Storage {

    private final File file;
    private final String filePath;

    /**
     * Creates a storage.
     *
     * @param filePath Directory path of file.
     */
    public Storage(String filePath) throws IOException {
        File tempFile = new File(filePath);

        boolean doesNotExist = !tempFile.exists();

        if (doesNotExist) {
            tempFile.createNewFile();
        }

        this.file = tempFile;
        this.filePath = filePath;
    }

    /**
     * Converts file into a list of task.
     *
     * @return List containing the task.
     * @throws FileNotFoundException If file does not exist.
     */
    public ArrayList<Task> getList() throws FileNotFoundException, BobInvalidDateAndTimeException {
        Scanner scanner = new Scanner(this.file);
        ArrayList<Task> list = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split("/");
            switch (split[0]) {
            case "T": {
                String description = split[2];
                Task task = split[1].equals("0")
                        ? new Task(description)
                        : new Task(description).markDone();
                list.add(task);
                break;
            }
            case "D": {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Deadline deadline = split[1].equals("0")
                        ? new Deadline(description, date, time)
                        : new Deadline(description, date, time).markDone();
                list.add(deadline);
                break;
            }
            case "E": {
                String description = split[2];
                String date = split[3];
                String time = split[4];
                Event event = split[1].equals("0")
                        ? new Event(description, date, time)
                        : new Event(description, date, time).markDone();
                list.add(event);
                break;
            }
            default:
                assert false;
            }
        }
        scanner.close();
        return list;
    }

    /**
     * Updates the file data based on the list.
     *
     * @param list Bob's task list.
     * @throws IOException If error occurs while writing the file.
     */
    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        StringBuilder textToAdd = new StringBuilder();

        for (Task task : list) {
            textToAdd.append(task.convertToStringData()).append(System.lineSeparator());
        }

        fw.write(textToAdd.toString());
        fw.close();
    }
}
