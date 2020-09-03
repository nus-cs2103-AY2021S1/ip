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

    /**
     * Creates a file if the file does not exist. Otherwise, it will use the existing file.
     * @throws IOException If the file cannot be created.
     */
    private void createFile() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return;
    }

    /**
     * Returns a list of task to be loaded into the task list after
     * it had retrieved the existing tasks in the memory file.
     * @return A list of task to be loaded into task list.
     * @throws IOException If the file cannot be created.
     */
    public List<Task> load() throws IOException {
        this.createFile();
        File file = new File(this.filePath);
        Scanner sc = new Scanner(file);
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            Task taskToAdd = processLine(sc.nextLine());
            taskList.add(taskToAdd);
        }
        return taskList;
    }

    /**
     * Processes the line from the file and returns the task represented
     * by the line
     * @param lineFromFile
     * @return the associated task
     */
    private Task processLine(String lineFromFile) {
        //Format of each line: e.g. T|X|description or D|X|description|date|time
        String[] taskComponent = lineFromFile.split(Pattern.quote("|"));
        switch (taskComponent[0]) {
        case "T":
            Task todoTask = new Todo(taskComponent[2]);
            if (taskComponent[1].equals("\u2713")) {
                todoTask.markAsDone();
            }
            return todoTask;
        case "D":
            Task deadlineTask = new Deadline(taskComponent[2],
                    LocalDate.parse(taskComponent[3], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    LocalTime.parse(taskComponent[4], DateTimeFormatter.ofPattern("hh.mma")));
            if (taskComponent[1].equals("\u2713")) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;
        case "E":
            Task eventTask = new Event(taskComponent[2],
                    LocalDate.parse(taskComponent[3], DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    LocalTime.parse(taskComponent[4].split(" to ")[0], DateTimeFormatter.ofPattern("hh.mma")),
                    LocalTime.parse(taskComponent[4].split(" to ")[1], DateTimeFormatter.ofPattern("hh.mma")));
            if (taskComponent[1].equals("\u2713")) {
                eventTask.markAsDone();
            }
            return eventTask;
        default:
            return null;
        }
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
