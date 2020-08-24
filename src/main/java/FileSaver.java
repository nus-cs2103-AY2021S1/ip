import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSaver {

    public static final String FILE_PATH = "duke/localData/data.duke";

    public static void save(TaskList list, String filePath) throws DukeException {
        String[] directories = filePath.split("/");
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, directories);
        if (!Files.exists(path.getParent())) {
            boolean directoriesCreated = path.getParent().toFile().mkdirs();
            if (!directoriesCreated) {
                throw new DukeException("I got lost somewhere in your folders.");
            }
        }
        try {
            FileWriter writer = new FileWriter(path.toString());
            for (Task item : list) {
                writer.write(item.encode() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I didn't have enough strength to move the bits.");
        }
    }

    // TODO: Consider moving decoding switch statement to its own method or under a util class.
    public static TaskList load(String filePath) {
        try {
            String[] directories = filePath.split("/");
            String home = System.getProperty("user.home");
            Path path = Paths.get(home, directories);
            File history = new File(path.toString());
            Scanner sc = new Scanner(history);
            TaskList loadTaskList = new TaskList();
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
                    throw new DukeException("There's something wrong with my memory...");
                }
                loadTaskList.add(loadedTask);
            }
            return loadTaskList;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("There's something wrong with my memory...");
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
    }
}
