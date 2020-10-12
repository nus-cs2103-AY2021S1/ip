package duke;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to manage loading and saving of tasks in text file
 */
public class Storage {
    private String filePath;
    private String DEFAULT_FILE_PATH = "data/tasks.txt";
    private String directoryPath;
    private File data;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    
    /**
     * Constructs a storage object.
     *
     * @param filePath The file path.
     * @param directoryPath The directory path.
     */
    public Storage(String filePath, String directoryPath) {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File data = new File(DEFAULT_FILE_PATH);
            data.createNewFile();
            this.data = data;
            this.filePath = DEFAULT_FILE_PATH;
            this.directoryPath = directoryPath;
            assert directory.exists() : "Failed to create the directory";
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads saved tasks into list for use
     *
     * @return ArrayList containing all saved tasks
     */
    public ArrayList<Task> getTasks() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner dataScanner = new Scanner(data);
            while (dataScanner.hasNext()) {
                String next = dataScanner.nextLine();
                char taskType = next.charAt(1);
                boolean isDone = next.charAt(4) == '\u2713';
                String description = next.substring(7);
                if (taskType == 'T') {
                    list.add(new ToDo(description, isDone));
                } else if (taskType == 'D') {
                    String[] split = description.split("[(]by: ");
                    String deadlineDesc = split[0];
                    LocalDate deadline = LocalDate.parse(split[1].substring(0, split[1].length() - 1), formatter);
                    list.add(new Deadline(deadlineDesc, deadline, isDone));
                } else if (taskType == 'E') {
                    String[] split = description.split("[(]at: ");
                    String eventDesc = split[0];
                    LocalDate eventTime = LocalDate.parse(split[1].substring(0, split[1].length() - 1), formatter);
                    list.add(new Event(eventDesc, eventTime, isDone));
                } else {
                    throw new DukeException("File reading error");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Writes tasks into file
     *
     * @param tasks List of tasks to be saved
     */
    public void updateFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            ArrayList<Task> list = tasks.getList();
            for (Task task: list) {
                String taskString = task.toString();
                fw.write(taskString + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
