import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage of the client data. Every storage instance will have a file path. Contains methods to save or
 * load from disk.
 */
public class Storage {
    private final Path filepath;

    /**
     * Public Constructor.
     *
     * @param filepath Path of data storage file.
     */
    public Storage(Path filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the current list of tasks to the disk.
     *
     * @param taskList List of tasks.
     * @throws IOException If an error occurred when saving to disk.
     */
    void updateMemory(ArrayList<Task> taskList) throws IOException {
        StringBuilder taskListString = new StringBuilder();
        for (Task task : taskList) {
            for (String attribute : task.attributeList()) {
                taskListString.append(attribute).append("\n");
            }
        }
        Files.writeString(filepath, taskListString, StandardOpenOption.WRITE);

    }

    /**
     * Loads the tasks that was previously saved to disk. If the file or directory cannot be found, it will be created
     * and a new list is returned.
     *
     * @return List populated with previously saved tasks, otherwise returns a new list if an existing one cannot be
     * found.
     * @throws BlankTaskException If the task provided in the list has a blank name.
     * @throws IOException        If there was an error in saving to disk.
     */
    ArrayList<Task> load() throws BlankTaskException, IOException {
        if (Files.notExists(filepath)) {
            if (Files.notExists(filepath.getParent())) {
                Files.createDirectories(filepath.getParent());
            }
            Files.createFile(filepath);
            return new ArrayList<>();
        } else {
            Scanner data;
            data = new Scanner(filepath);
            ArrayList<Task> taskList = new ArrayList<>();
            while (data.hasNextLine()) {
                Task curr;
                switch (data.nextLine()) {
                case "T":
                    curr = new ToDo(data.nextLine());
                    break;
                case "E":
                    curr = new Event(data.nextLine(), LocalDate.parse(data.nextLine()),
                            LocalTime.parse(data.nextLine()));
                    break;
                default:
                    curr = new Deadline(data.nextLine(), LocalDate.parse(data.nextLine()),
                            LocalTime.parse(data.nextLine()));
                    break;
                }
                if (data.nextBoolean()) {
                    curr.markAsDone();
                }
                taskList.add(curr);
                data.nextLine();
            }
            return taskList;
        }
    }
}
