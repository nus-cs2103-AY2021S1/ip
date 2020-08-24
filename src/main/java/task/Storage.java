package task;

import exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private final Path path;
    private static final String DEFAULT_FILEPATH = "./data/duke.txt";

    public Storage() {
        this.path = Paths.get(DEFAULT_FILEPATH);
        File file = new File(DEFAULT_FILEPATH);
        file.getParentFile().mkdirs();
    }

    public TaskManager load() {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskManager();
        } else {
            try {
                return DataTranslator.decode(Files.readAllLines(path));
            } catch(FileNotFoundException e){
                throw new AssertionError("A non-existent file scenario has been handled earlier.");
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return new TaskManager();
            }
        }
    }
    public void save(TaskManager taskManger) throws DukeException {
        try {
            List<String> encodedData = DataTranslator.encode(taskManger);
            Files.write(path, encodedData);
        } catch(IOException e){
            throw new DukeException(e.getMessage());
        }
    }


}
