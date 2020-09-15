import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a strorage systems that stores the input information
 */
public class Storage {
    /**
     * The default relative path to the saving file.
     */
    private static final String DEFAULT_PATH = "./data/duke.txt";
    /**
     * Stores the path object with the default path.
     */
    private final Path path;

    /**
     * Constructs the storage class.
     */
    public Storage() throws IOException {
        path = Paths.get(DEFAULT_PATH);
        File file = new File(DEFAULT_PATH);
        file.getParentFile().mkdirs();

    }

    /**
     * Constructs a Tasklist of the tasks that are present in the storage.
     *
     * @return Tasklist of the previous tasks that have been stored.
     */
    public TaskList load() {
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        try {
            return convertTextToTask(Files.readAllLines(path));
        } catch (FileNotFoundException e) {
            e.getMessage();
            return new TaskList();
        } catch (IOException e) {
            e.getMessage();
            return new TaskList();
        }
    }

    /**
     * Constructs a Tasklist of the tasks that are present in the storage.
     *
     * @param tasklist Contains the list of all the tasks.
     * @return Arraylist that will be coverted from a tasklist object.
     */
    public ArrayList<String> convertArrayToSaveFormat(TaskList tasklist) {
        ArrayList<String> strings = new ArrayList<>();
        for (Task tasks : tasklist.getTasks()) {
            strings.add(tasks.writeSaveFormat());
        }
        return strings;
    }

    /**
     * Converts a list of strings to tasks.
     *
     * @param lines lines of strings that represents the tasks.
     * @return Tasklist generated from the list of strings that have been given.
     */
    public TaskList convertTextToTask(List<String> lines) {
        TaskList tasks = new TaskList();
        try {
            for (String line : lines) {
                String[] currLine = line.split(" \\| ");
                Task currTask = null;
                if (currLine[0].equals("T")) {
                    currTask = new ToDo(currLine[2]);
                    if (currLine.length == 4) {
                        currTask.makeTag(currLine[3]);
                    }
                } else if (currLine[0].equals("D")) {
                    currTask = new Deadline(currLine[2], currLine[3]);
                    if (currLine.length == 5) {
                        currTask.makeTag(currLine[4]);
                    }
                } else if (currLine[0].equals("E")) {
                    currTask = new Event(currLine[2], currLine[3]);
                    if (currLine.length == 5) {
                        currTask.makeTag(currLine[4]);
                    }
                }
                if (currLine[1].equals("1")) {
                    currTask.markAsDone();
                }
                tasks.addTask(currTask);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.getMessage();
        }
        return tasks;

    }

    /**
     * Saves the list of strings into the folder
     *
     * @param strings Strings of the different tasks.
     */
    public void save(ArrayList<String> strings) {
        try {
            Files.write(path, strings);
        } catch (IOException e) {
            e.getMessage();
        }

    }
}
