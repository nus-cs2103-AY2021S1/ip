import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

/**
 * Storage class handles saving and loading of data from the hardrive
 */
public class Storage {

    public static final String FILE_PATH = "duke/data/list.duke";

    /**
     * saves the list of items in a hardcoded file called list.duke
     * @param list TaskList object to be saved
     * @param filePath path to save file at
     * @throws DukeException
     */
    public static void save(TaskList list, String filePath) throws DukeException {
        String[] directories = filePath.split("/");
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, directories);
        if (!Files.exists(path.getParent())) {
            boolean directoriesCreated = path.getParent().toFile().mkdirs();
            if (!directoriesCreated) {
                throw new DukeException("unable to create directory");
            }
        }
        try {
            FileWriter writer = new FileWriter(path.toString());
            for (Task item : list) {
                writer.write(item.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("IOexception detected");
        }
    }

    /**
     * loads from list.duke form specified filePath
     * @param filePath filePath to search
     * @return TaskList containing all saved additions to the list
     */
    public static TaskList load(String filePath) {
        try {
            String[] directories = filePath.split("/");
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, directories);
            File history = new File(path.toString());
            Scanner sc = new Scanner(history);
            TaskList loadStore = new TaskList();
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
                        throw new DukeException("Unable to find");
                }
                loadStore.add(loadedTask);
            }
            return loadStore;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("String Index out of bounds");
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }

}
