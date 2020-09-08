package nekochan.storage;

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

import nekochan.exceptions.NekoStorageException;
import nekochan.model.task.Deadline;
import nekochan.model.task.Event;
import nekochan.model.task.Task;
import nekochan.model.task.TaskList;
import nekochan.model.task.ToDo;
import nekochan.util.Messages;

/**
 * The {@code Storage} class provides persistent local storage for tasks stored in the program.
 */
public class Storage {

    /**
     * The default local file path to be used.
     */
    public static final String FILE_PATH = "nekochan/localData/data.neko";

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
     * @throws NekoStorageException if the file cannot be written to.
     */
    public void save(TaskList list) throws NekoStorageException {
        String[] directories = filePath.split("/");
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, directories);
        if (!Files.exists(path.getParent())) {
            boolean directoriesCreated = path.getParent().toFile().mkdirs();
            if (!directoriesCreated) {
                throw new NekoStorageException(Messages.STORAGE_ERROR_FOLDER_ERROR);
            }
        }
        try {
            FileWriter writer = new FileWriter(path.toString());
            list.getStream().forEach((item) -> {
                try {
                    writer.write(item.encode() + "\n");
                } catch (IOException e) {
                    throw new NekoStorageException(Messages.STORAGE_ERROR_UNABLE_TO_WRITE);
                }
            });
            writer.close();
        } catch (IOException e) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_UNABLE_TO_WRITE);
        }
    }

    /**
     * Returns a list of tasks loaded from the local save file.
     *
     * @return a list of tasks loaded from the local save file.
     * @throws NekoStorageException if the file does not exist or its content corrupted.
     */
    public List<Task> load() throws NekoStorageException {
        try {
            String[] directories = filePath.split("/");
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, directories);
            File history = new File(path.toString());

            Scanner sc = new Scanner(history);
            List<Task> temporaryList = new ArrayList<>();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task loadedTask;
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
                    throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
                }

                assert loadedTask != null : "loaded task should not be null";

                temporaryList.add(loadedTask);
            }
            return temporaryList;
        } catch (StringIndexOutOfBoundsException e) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_CORRUPT);
        } catch (FileNotFoundException e) {
            throw new NekoStorageException(Messages.STORAGE_ERROR_MISSING_SAVE);
        }
    }
}
