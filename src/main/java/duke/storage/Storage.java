package duke.storage;

import duke.exceptions.InvalidFIlePathException;
import duke.exceptions.NoSuchTaskException;
import duke.exceptions.TaskListTranslatorException;
import duke.tasklist.TaskList;

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
        this(DEFAULT_FILEPATH);
    }

    public Storage(String filePath) throws InvalidFIlePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidFIlePathException();
        }
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public TaskList load() throws TaskListTranslatorException {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        try {
            return TaskListTranslator.decode(Files.readAllLines(path));
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario has been handled earlier.");
        } catch (IOException e) {
            // todo: throw duke.storage exception
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    public void save(TaskList taskList) {
        try {
            List<String> encodedTaskList = TaskListTranslator.encode(taskList);
            Files.write(path, encodedTaskList);
        } catch (IOException e) {
            // todo: io exception
            throw new NoSuchTaskException();
        }
    }

}