import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final Path DUKE_DATA_FILE_PATH = Paths.get("data", "duke.txt");
    private static final Path DUKE_DATA_DIR_PATH = Paths.get("data");
    private static final String NEW_LINE = "\n";
    private static final String PADDING = "      ";


    public static void loadTasksFromDisk(List<Task> taskList) throws FileNotFoundException, DukeException {
        File dukeDataFile = new File(DUKE_DATA_FILE_PATH.toUri());
        if (Files.notExists(DUKE_DATA_DIR_PATH)) {
            throw new DukeDataFolderException("Missing Deuk Data Folder!" + NEW_LINE + PADDING +
                    "Creating new Deuk Data Folder..."
            );
        }
        Scanner fs = new Scanner(dukeDataFile);
        while (fs.hasNext()) {
            String taskString = fs.nextLine();
            Scanner sc = new Scanner(taskString);
            Boolean isDone = sc.nextInt() == 1;
            String taskType = sc.next();
            String taskName = sc.next();
            Task task;
            if (taskType.equals("T")) {
                task = Todo.createTodo(taskName);
            } else if (taskType.equals("D")) {
                String dueDate = fs.nextLine();
                task = Deadline.createDeadline(taskName, dueDate);
            } else if (taskType.equals("E")) {
                String timing = fs.nextLine();
                task = Event.createEvent(taskName, timing);
            } else {
                throw new DukeException("Save file corrupted!");
            }
            task.setDoneness(isDone);
            taskList.add(task);
        }
//        System.out.println("full path: " + dukeDataFile.getAbsolutePath());
//        System.out.println("file exists?: " + dukeDataFile.exists());
//        System.out.println("is Directory?: " + dukeDataFile.isDirectory());
    }

    public static void saveTasksToDisk(List<Task> taskList) throws IOException {
        // TODO: check dirty flag before saving to disk
        FileWriter fw = new FileWriter(DUKE_DATA_FILE_PATH.toString());
        String tasksString = "";
        for (Task task : taskList) {
            tasksString += task.toSaveDataFormat() + NEW_LINE;
        }
        fw.write(tasksString);
        fw.close();
    }
}
