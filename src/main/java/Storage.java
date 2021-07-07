import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used for storing user data.
 */
public class Storage {
    protected static final String SRC = System.getProperty("user.dir");
    protected static final Path PATH = Paths.get(SRC, "data", "duke.txt");
    protected boolean hasDirectory;

    Storage() {
        this.hasDirectory = Files.exists(PATH);
    }

    /**
     * Save the TaskList data to the storage file.
     * @param tasks list of tasks given by the user
     * @throws DukeException if the storage file is not found
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        if (this.hasDirectory) {
            try {
                FileWriter fw = new FileWriter(PATH.toString());
                String newText = "";

                for (Task t : tasks) {
                    String tempType = t.getType();
                    String type;

                    switch (tempType) {
                    case "todo":
                        type = "T";
                        break;
                    case "event":
                        type = "E";
                        break;
                    case "deadline":
                        type = "D";
                        break;
                    case "doafter":
                        type = "DA";
                        break;
                    default:
                        throw new DukeException("Type not recognised");
                    }

                    int doneStatus = t.getDoneStatus() ? 1 : 0;
                    String description = t.getDescription();
                    String timing = t.getTiming();
                    newText += String.format("%s|%d|%s", type, doneStatus, description);

                    if (timing.equals("")) {
                        newText += "\n";
                    } else {
                        newText += String.format("|%s%n", timing);
                    }
                }

                fw.write(newText);
                fw.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } else {
            throw new DukeException("duke.txt not found");
        }
    }

    private TaskList loadTasks(String filePath) throws DukeException {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            TaskList tasks = new TaskList();

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] arr = data.split("\\|");
                String task = arr[0];
                String doneStatus = arr[1];
                boolean isDone = doneStatus.equals("1");
                String description = arr[2];
                String timing = arr.length == 4 ? arr[3] : "";
                Task t;

                switch (task) {
                case "T":
                    t = new ToDo(description);
                    break;
                case "E":
                    t = new Event(description, timing);
                    break;
                case "D":
                    t = new Deadline(description, timing);
                    break;
                case "DA":
                    t = new DoAfter(description, timing);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, "
                            + "but I don't recognise the command from the text file.");
                }

                if (isDone) {
                    t.markAsDone();
                }

                tasks.addTask(t);
            }

            return tasks;
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }

        return new TaskList();
    }

    /**
     * Loads TaskList data from the storage file, and returns it.
     * Creates a new storage file, and returns an empty TaskList if the file does not exist.
     * @return a TaskList
     */
    public TaskList loadFile() {
        if (this.hasDirectory) {
            try {
                return loadTasks(PATH.toString());
            } catch (Exception e) {
                System.err.println(e);
            }
        } else {
            try {
                File file = new File(PATH.toString());
                file.getParentFile().mkdir();
                file.createNewFile();
                this.hasDirectory = true;
                return new TaskList();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

        return new TaskList();
    }
}
