package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Encapsulates storing logic
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage class with specified file path.
     * @param filePath Location of the file where data is to be stored.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes tasks to file.
     * @param tasks Tasks to be written.
     * @throws IOException If an error occurs during writing.
     */
    public void writeToFile(TaskList tasks) throws IOException {
        assert tasks != null : "tasks have not been initialised";
        FileWriter file = new FileWriter(filePath);
        StringBuilder s = new StringBuilder();
        IntStream.range(0, tasks.size()).forEach(i -> {
            if (tasks.get(i) instanceof Todo) {
                s.append(String.format("T | %d | %s", tasks.get(i).getStatus() ? 1 : 0, tasks.get(i).getDescription()));
            } else if (tasks.get(i) instanceof Deadline) {
                s.append(String.format("D | %d | %s | %s",
                        tasks.get(i).getStatus() ? 1 : 0,
                        tasks.get(i).getDescription(), (
                                (Deadline) tasks.get(i)).getDate()));
            } else if (tasks.get(i) instanceof Event) {
                s.append(String.format("E | %d | %s | %s",
                        tasks.get(i).getStatus() ? 1 : 0,
                        tasks.get(i).getDescription(), (
                                (Event) tasks.get(i)).getDate()));
            }
            s.append("\n");
        });
        file.write(s.toString());
        file.close();
    }

    /**
     * Reads file and returns ArrayList of tasks stored.
     * @return ArrayList of tasks stored.
     * @throws DukeException If error occurs during reading file.
     */
    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(this.filePath);
        if (!file.exists()) {
            makeDirectory(file);
        }
        try {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String row = s.nextLine();
                String[] strArray = row.split(" \\| ");
                switch (strArray[0]) {
                case "T":
                    tasks.add(new Todo(strArray[2], "1".equals(strArray[1])));
                    break;
                case "D":
                    tasks.add(new Deadline(strArray[2], "1".equals(strArray[1]), LocalDate.parse(strArray[3])));
                    break;
                case "E":
                    tasks.add(new Event(strArray[2], "1".equals(strArray[1]), LocalDate.parse(strArray[3])));
                    break;
                default:
                    throw new DukeException("File in incorrect format.");
                }
            }
        } catch (IOException ex) {
            throw new DukeException("Error reading file.");
        }
        return tasks;
    }

    private void makeDirectory(File file) throws DukeException {
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException ex) {
            throw new DukeException("Failed to create file for storage.");
        }
    }
}
