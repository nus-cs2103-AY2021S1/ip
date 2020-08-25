package duke;

import duke.exceptions.DukeException;
import duke.task.*;

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

public class Storage {

    public static final String FILE_PATH = "duke/localData/data.duke";

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(TaskList list) throws DukeException {
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
    public List<Task> load() throws DukeException {
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
                    throw new DukeException("There's something wrong with my memory...");
                }
                temporaryList.add(loadedTask);
            }
            return temporaryList;
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("There's something wrong with my memory...");
        } catch (FileNotFoundException e) {
            throw new DukeException("I lost my memories.");
        }
    }
}
