import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Storage. The Storage class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /**
     * The filePath of package.
     */
    private static String filePath;
    /**
     * The user interface.
     */
    private static Ui ui;

    /**
     * Creates a new <code>Storage</code> with the given <code>filepath</code>.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.ui = new Ui();
        initiateFile();
    }

    /**
     * Initiates the file.
     **/
    private void initiateFile() {
        try {
            if (Files.notExists(Paths.get(filePath))) {
                Files.createDirectory(Paths.get(filePath));
            }
            if (Files.notExists(Paths.get(filePath + "/Duke.txt"))) {
                Files.createFile(Paths.get(filePath + "/Duke.txt"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     * @return the task list.
     * @throws FileNotFoundException when the filePath is invalid.
     */
    public static ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath + "/Duke.txt");
        Scanner sc = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            String[] data = sc.nextLine().split(" \\| ");
            if (data[0].equals("T")) {
                ToDo todo = new ToDo(data[2]);
                tasks.add(todo);
                if (data[1].equals("1")) {
                    todo.setDone();
                }
            } else if (data[0].equals("D")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime time = LocalDateTime.parse(data[3], formatter);
                Deadline deadline = new Deadline(data[2], time);
                tasks.add(deadline);
                if (data[1].equals("1")) {
                    deadline.setDone();
                }
            } else if (data[0].equals("E")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime time = LocalDateTime.parse(data[3], formatter);
                Event event = new Event(data[2], time);
                tasks.add(event);
                if (data[1].equals("1")) {
                    event.setDone();
                }
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the file.
     * @throws IOException ioexception.
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath + "/Duke.txt");
        for (int i = 0; i < tasks.size(); i++)  {
            Task task = tasks.get(i);
            if (task instanceof ToDo) {
                String text = "T | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand();
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Deadline) {
                String text = "D | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand() + " | "
                        + ((Deadline) task).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                fileWriter.write(text + System.lineSeparator());
            } else if (task instanceof Event) {
                String text = "E | " + (task.getStatus() ? "1" : "0") + " | " + task.getCommand() + " | "
                        + ((Event) task).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                fileWriter.write(text + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    /**
     * Tries to record the changes tasks to the file.
     * @param tasks the list of task to be written to file.
     */
    public static void record(ArrayList<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println(ui.showIOException(e));
        }
    }

}