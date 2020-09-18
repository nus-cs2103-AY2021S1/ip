import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private Path filePath;
    private Path dirPath;

    public Storage(Path filePath, Path dirPath) {
        this.filePath = filePath;
        this.dirPath = dirPath;

    }

    /**
     * Returns a list of tasks after loading them  from the duke data text file.
     * @return A list of tasks loaded from the duke data text file.
     * @throws FileNotFoundException If the duke data text file is missing.
     * @throws DukeDataFolderException If the duke data folder is missing.
     * @throws DukeException If the duke data text file does not follow the save format possibly due to
     * file corruption.
     */
    List<Task> loadTasksFromDisk() throws FileNotFoundException, DukeException, DukeDataFolderException {
        List<Task> taskList = new ArrayList<>();
        File dukeDataFile = new File(this.filePath.toUri());
        if (Files.notExists(this.dirPath)) {
            throw new DukeDataFolderException("Missing Deuk Data Folder!" + Ui.NEW_LINE
                    + Ui.PADDING + "Creating new Deuk Data Folder...");
        }
        Scanner fs = new Scanner(dukeDataFile);
        while (fs.hasNext()) {
            String taskString = fs.nextLine();
            Scanner sc = new Scanner(taskString);
            Boolean isDone = sc.nextInt() == 1;
            String taskType = sc.next();
            String taskName = sc.next();
            Task task;
            switch (taskType) {
                case "T":
                    task = Todo.createTodo(taskName);
                    break;
                case "D":
                    String dueDate = fs.nextLine();
                    task = Deadline.createDeadline(taskName, dueDate);
                    break;
                case "E":
                    String timing = fs.nextLine();
                    task = Event.createEvent(taskName, timing);
                    break;
                default:
                    throw new DukeException("Save file corrupted!");
            }
            task.setDoneness(isDone);
            taskList.add(task);
        }
        return taskList;
    }

    /**
     * Save the tasks in the TaskList instance to the duke data text file on disk.
     * @param tasks TaskList instance used by the Duke instance.
     * @throws IOException  If the duke data text file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    void saveTasksToDisk(TaskList tasks) throws IOException {
        // TODO: check dirty flag before saving to disk
        FileWriter fw = new FileWriter(this.filePath.toString());
        String tasksString = "";
        for (int i = 0; i < tasks.countTotalTasks(); i++) {
            try {
                assert i < tasks.countTotalTasks() : "Index of task is outside the range of task list";
                Task task = tasks.getTask(i);
                tasksString += task.getSaveDataString() + Ui.NEW_LINE;
            } catch (IndexOutOfBoundsException ex) {
                Ui.printError("Saving tasks to disk encountered IndexOutOfBoundsException");
                break;
            }
        }
        fw.write(tasksString);
        fw.close();
    }


    /**
     * Creates duke data folder for storing the duke data text file and the duke data
     * text file in the created folder.
     */
    void createDukeDataFolder() {
        File dir = new File(this.dirPath.toUri());
        boolean isCreated = dir.mkdir();
        if (isCreated) {
            Ui.print("Successfully created Deuk Data Folder");
            try {
                FileWriter fw = new FileWriter(this.filePath.toString());
                fw.close();
            } catch (IOException err) {
                Ui.printError(err.getMessage());
            }
        } else {
            Ui.printError("Failed to create Deuk Data Folder");
        }
    }

    /**
     * Creates the duke data text file in the duke data folder.
     */
    void createDukeDataFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath.toString());
            fw.close();
        } catch (IOException err) {
            Ui.printError(err.getMessage());
        }
    }
}
