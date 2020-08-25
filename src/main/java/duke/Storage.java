package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read the URL path to the locally saved tasks.
 *
 * @author Kor Ming Soon
 */
public class Storage {

    private final static String DEFAULT_PATH = "./duke.txt";
    private final Path path;

    /**
     * Constructor for the Storage object to track locally saved tasks.
     * The storage object checks for any pre-existing files to load information.
     * If not available, the storage object will create a file.
     */
    public Storage() {
        path = Paths.get(DEFAULT_PATH);
        File file = new File(DEFAULT_PATH);
        if (Files.notExists(this.path)) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for the storage to write the current session's task list locally into the computer.
     * @param taskList List of task to be copied into the local computer.
     * @throws IOException
     */
    public void writeData(List<Task> taskList) throws IOException {
        FileWriter file = new FileWriter(DEFAULT_PATH);
        for (Task tasking : taskList) {
            String toBeSaved = "";
            switch (tasking.getTasktype()) {
                case TODO:
                    toBeSaved = String.format("%s\t%s\t%s",
                            tasking.getTasktype(),
                            tasking.isTaskDone(),
                            tasking.getTask());
                    break;
                case EVENT:
                case DEADLINE:
                    toBeSaved = String.format("%s\t%s\t%s\t%s",
                            tasking.getTasktype(),
                            tasking.isTaskDone(),
                            tasking.getTask(),
                            tasking.getDuration());
                    break;
            }
            file.write(toBeSaved + "\n");
        }
        file.close();
    }

    /**
     * Storage to search and load from the local computer a list of tasks.
     * @return Returns list of tasks that was historically written existing in the computer.
     * @throws IOException When there is no save file found.
     */
    public List<Task> load() throws IOException {
        // checks to see if a file is already supposed to be there
        Scanner sc = new Scanner(path.toFile());
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String[] storedTask = sc.nextLine().split("\t");
            String tasktype = storedTask[0];
            boolean isDone = storedTask[1].equals("true");
            Task taskToBeAdded = null;
            switch (tasktype) {
                case "T":
                    taskToBeAdded = new Todo(storedTask[2], isDone);
                    break;
                case "D":
                    taskToBeAdded = new Deadline(storedTask[2], storedTask[3], isDone);
                    break;
                case "E":
                    taskToBeAdded = new Event(storedTask[2], storedTask[3], isDone);
                    break;
            }
            taskList.add(taskToBeAdded);
        }
        return taskList;
    }
}

