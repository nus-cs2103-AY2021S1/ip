import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Represents a memory of the tasks of user. It loads tasks from file, saves and updates
 * the file.
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    //This function creates file if it does not exist, else return

    /**
     * Creates a file if the file does not exist. Otherwise, it will use the existing file.
     * @throws IOException If the file cannot be created.
     */
    private void createFile() throws IOException {
        File f = new File(this.filePath);
        if (f.exists()) {
            return;
        } else {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
    }

    /**
     * Returns a list of task to be loaded into the task list after
     * it had retrieved the existing tasks in the memory file.
     * @return A list of task to be loaded into task list.
     * @throws IOException If the file cannot be created.
     */
    public List<Task> load() throws IOException {
        this.createFile();
        File f = new File(this.filePath);
        Scanner sc = new Scanner(f);
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            //Format of each line: e.g. T|X|description or D|X|description|date|time
            String line = sc.nextLine();
            String[] taskComponent = line.split(Pattern.quote("|"));
            switch (taskComponent[0]) {
            case "T":
                Task todoTask = new Todo(taskComponent[2]);
                taskList.add(todoTask);
                if (taskComponent[1].equals("\u2713")) {
                    todoTask.markAsDone();
                }
                break;
            case "D":
                Task deadlineTask = new Deadline(taskComponent[2],
                        LocalDate.parse(taskComponent[3], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalTime.parse(taskComponent[4], DateTimeFormatter.ofPattern("hh.mma")));
                taskList.add(deadlineTask);
                if (taskComponent[1].equals("\u2713")) {
                    deadlineTask.markAsDone();
                }
                break;
            case "E":
                Task eventTask = new Event(taskComponent[2],
                        LocalDate.parse(taskComponent[3], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                        LocalTime.parse(taskComponent[4].split(" to ")[0], DateTimeFormatter.ofPattern("hh.mma")),
                        LocalTime.parse(taskComponent[4].split(" to ")[1], DateTimeFormatter.ofPattern("hh.mma")));
                taskList.add(eventTask);
                if (taskComponent[1].equals("\u2713")) {
                    eventTask.markAsDone();
                }
                break;
            }
        }
        return taskList;
    }

    /**
     * Writes the input text to the file.
     * @param textToAdd The text to be added to the file.
     * @throws IOException If the writing of file fails.
     */
    private void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Updates file content when the task list is updated.
     * @param text The content of the task list to overwrite in the file.
     * @throws IOException If the writing of file fails.
     */
    public void updateFile(String text) throws IOException {
        writeToFile(text);
    }
}
