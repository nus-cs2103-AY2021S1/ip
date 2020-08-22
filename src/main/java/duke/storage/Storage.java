package duke.storage;

import duke.exceptions.NoSuchTaskException;
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
        // todo: check for directory and non existence of file
        path = Paths.get(DEFAULT_FILEPATH);
        File file = new File(DEFAULT_FILEPATH);
        file.getParentFile().mkdirs();
    }

    public TaskList load() {
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