import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read the URL path to the locally saved tasks.
 * @author Kor Ming Soon
 */
public class Storage {

    private final Path path;
    private final static String DEFAULT_PATH = "./duke.txt";

    /**
     * Constructor for the Storage object to track locally saved tasks.
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

    public void writeData(List<Task> taskList) throws IOException {
        FileWriter file = new FileWriter(DEFAULT_PATH);
        for (Task tasking : taskList) {
            String toBeSaved = "";
            switch (tasking.getTasktype()) {
                case TODO:
                    // if there is only todo, means only need retrieve event detail
                    toBeSaved = String.format("%s\t%s\t%s",
                            tasking.getTasktype(),
                            tasking.isTaskDone(),
                            tasking.getTask());
                    break;
                case EVENT:
                case DEADLINE:
                    // event or deadline, means must retrieve, duration and detail of event
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

    public List<Task> load() throws IOException {
        // checks to see if a file is already supposed to be there
        Scanner sc = new Scanner(path.toFile());
        List<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String[] storedTask =  sc.nextLine().split("\t");
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

