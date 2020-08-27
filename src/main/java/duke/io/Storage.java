package duke.io;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Storage class define rules for create, read and write to file.
 *
 * @author Galvin Leow Wen Yuan
 * @author A0200204J
 * @version v1.0
 */
public class Storage {
    protected String filePath;

    /**
     * Class constructor.
     * Initialise Storage class with filepath.
     *
     * @param filePath use to create, read and write a file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Read file and convert it into Arraylist<Task>.
     *
     * @return Arraylist<Task> contains all the task from the file.
     * @throws DukeException exception for file cannot be found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        File file = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String fileLine;
            while ((fileLine = br.readLine()) != null) {
                String[] tempArr = fileLine.split(",");
                String command = tempArr[0];
                switch (command) {
                    case "duke.task.Todo":
                        taskArrayList.add(new Todo(tempArr[2]));
                        break;
                    case "duke.task.Deadline":
                        Task tempDeadline = new Deadline(tempArr[2], LocalDateTime.parse(tempArr[3]));
                        if (tempArr[1].equals("true")) {
                            tempDeadline.markAsDone();
                        }
                        taskArrayList.add(tempDeadline);
                        break;
                    case "duke.task.Event":
                        Task tempEvent =
                                new Event(
                                        tempArr[2], LocalDateTime.parse(tempArr[3]), LocalDateTime.parse(tempArr[4]));
                        if (tempArr[1].equals("true")) {
                            tempEvent.markAsDone();
                        }
                        taskArrayList.add(tempEvent);
                        break;
                    default:
                        System.err.println("No event of this type");
                }
            }
        } catch (IOException fileNotFoundException) {
            throw new DukeException("Failed to find file: " + fileNotFoundException.getMessage());
        }
        return taskArrayList;
    }

    /**
     * Convert from Tasklist Arraylist<Task> and write into file.
     *
     * @param taskList data to be written to file.
     */
    public void write(TaskList taskList) {
        createFile();
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList.taskArrayList) {
                String taskType = task.getClass().getTypeName();
                if (taskType.equals("duke.task.Todo")) {
                    writer.append(String.format("%s,%s,%s", taskType, task.isDone, task.description));
                } else if (taskType.equals("duke.task.Deadline")) {
                    writer.append(
                            String.format(
                                    "%s,%s,%s,%s", taskType, task.isDone, task.description, ((Deadline) task).by));
                } else {
                    writer.append(
                            String.format(
                                    "%s,%s,%s,%s,%s",
                                    taskType, task.isDone, task.description, ((Event) task).at, ((Event) task).end));
                }
                writer.write("\n");
            }
            writer.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Check path exist.
     * If exist do nothing, else create.
     */
    private void createFile() {
        Path path = Paths.get(filePath).getParent();
        if (Files.exists(path)) {
            try {
                File file = new File(filePath);
                if (file.createNewFile()) {
                    System.out.println("File created at: " + file);
                } else {
                    System.out.println("File already exist at: " + file);
                }
            } catch (IOException e) {
                System.err.println("Failed to create file: " + e.getMessage());
            }
        } else {
            try {
                Files.createDirectories(path);
                System.out.println("Directory created: " + path);
            } catch (IOException e) {
                System.err.println("Failed to create directory: " + e.getMessage());
            }
            createFile();
        }
    }
}
