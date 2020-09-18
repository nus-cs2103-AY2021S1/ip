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

    List<Task> loadTasksFromDisk() throws FileNotFoundException, DukeException {
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

    void saveTasksToDisk(TaskList tasks) throws IOException {
        // TODO: check dirty flag before saving to disk
        FileWriter fw = new FileWriter(this.filePath.toString());
        String tasksString = "";
        for (int i = 0; i < tasks.countTotalTasks(); i++) {
            Task task = tasks.getTask(i);
            tasksString += task.getSaveDataString() + Ui.NEW_LINE;
        }
        fw.write(tasksString);
        fw.close();
    }


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

    void createDukeDataFile() {
        try {
            FileWriter fw = new FileWriter(this.filePath.toString());
            fw.close();
        } catch (IOException err) {
            Ui.printError(err.getMessage());
        }
    }
}
