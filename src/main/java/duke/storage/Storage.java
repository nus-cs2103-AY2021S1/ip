package duke.storage;

import duke.exceptions.DukeStorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code Storage} class provides persistent local storage for tasks stored in the program.
 */
public class Storage {

    /**
     * The default local file path to be used.
     */
    public static final String FILE_PATH = "duke/localData/data.duke";

    private String filePath;

    /**
     * Constructs a {@code Storage} object from the specified file path.
     *
     * @param filePath the file path to store the local save file.
     *                 Relative to the user's home directory.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the specified list of tasks to the local save file.
     *
     * @param list the {@link TaskList} to be saved.
     * @throws DukeStorageException if the file cannot be written to.
     */
    public void save(TaskList list) throws DukeStorageException {
        String[] directories = filePath.split("/");
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, directories);
        if (!Files.exists(path.getParent())) {
            boolean directoriesCreated = path.getParent().toFile().mkdirs();
            if (!directoriesCreated) {
                throw new DukeStorageException("I got lost somewhere in your folders.");
            }
        }
        try {
            FileWriter writer = new FileWriter(path.toString());
            for (Task item : list) {
                writer.write(item.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeStorageException("I didn't have enough strength to move the bits.");
        }
    }

    // TODO: Consider moving decoding switch statement to its own method or under a util class.

    /**
     * Returns a list of tasks loaded from the local save file.
     *
     * @return a list of tasks loaded from the local save file.
     * @throws DukeStorageException if the file does not exist or its content corrupted.
     */
    public List<Task> load() throws DukeStorageException {
        try {
            String[] directories = filePath.split("/");
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, directories);
            File history = new File(path.toString());
            Scanner sc = new Scanner(history);
            List<Task> temporaryList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task loadedTask = null;
                switch (line.charAt(0)) {
                case 'E':
                    loadedTask = Event.decode(line);
                    break;
                case 'D':
                    loadedTask = Deadline.decode(line);
                    break;
                case 'T':
                    loadedTask = ToDo.decode(line);
                    break;
                default:
                    throw new DukeStorageException("There's something wrong with my memory...");
                }
                temporaryList.add(loadedTask);
            }
            return temporaryList;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeStorageException("There's something wrong with my memory...");
        } catch (FileNotFoundException e) {
            throw new DukeStorageException("I lost my memories.");
        }
    }
}
