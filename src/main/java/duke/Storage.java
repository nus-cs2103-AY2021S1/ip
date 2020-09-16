package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * This class deals with the operations regarding the data stored and needed by the programme
 */
public class Storage {
    private String filePath;
    private Scanner sc;

    /**
     * Initialises a Storage object that represents the data stored in the file path provided
     *
     * @param filePath File path containing the location where the data for the program is stored
     * @throws IOException If there is an error in Files operations
     */
    public Storage(String filePath) throws IOException {
        // make file path directory if non existent
        File file = new File(filePath);
        file.getParentFile().mkdirs();

        // create file if it does not exist
        if (!file.exists()) {
            file.createNewFile();
        }
        this.filePath = filePath;
    }

    /**
     * Loads the data in the file into a List for easier manipulation
     *
     * @return List containing the tasks represented by each line in the data
     * @throws FileNotFoundException If file is not found
     * @throws DukeException If the data contains invalid tasks
     */
    public List<Task> load() throws IOException, DukeException {
        List<Task> tasks = new ArrayList<>();
        List<String> linesOfFile = new ArrayList<>();
        linesOfFile = Files.readAllLines(Paths.get(filePath));

        for (String inputLine : linesOfFile) {
            String[] parsedTask = inputLine.split(" \\| ");
            Task newTask = null;
            String taskName = parsedTask[0];

            switch (InputType.getCommand(taskName)) {
            case TODO:
                if (parsedTask.length < 3) {
                    throw DukeException.badToDo();
                }
                newTask = new ToDo(parsedTask[2]);
                break;
            case DEADLINE:
                if (parsedTask.length < 3) {
                    throw DukeException.badDeadlineTask();
                } else if (parsedTask.length < 4) {
                    throw DukeException.badDeadlineDate();
                }
                newTask = new Deadline(parsedTask[2], parsedTask[3]);
                break;
            case EVENT:
                if (parsedTask.length < 3) {
                    throw DukeException.badEventTask();
                } else if (parsedTask.length < 4) {
                    throw DukeException.badEventDate();
                }
                newTask = new Event(parsedTask[2], parsedTask[3]);
                break;
            default:
                throw DukeException.badCommand();
            }

            if (parsedTask[1].equals("1")) {
                newTask.doTask();
            }

            tasks.add(newTask);
        }

        return tasks;
    }

    /**
     * Stores the provided TaskList into the data file
     *
     * @param tasks TaskList containing the data to be persisted to the hard disk
     * @throws IOException If there is an error in Files operations
     */
    public void storeTaskList(TaskList tasks) throws IOException {
        Files.writeString(Paths.get(filePath), ""); // clear storage

        for (int i = 0; i < tasks.numTasks(); i++) {
            String formattedData = i == 0
                    ? tasks.getTask(i).getStorageFormat()
                    : "\n" + tasks.getTask(i).getStorageFormat();

            Files.writeString(Paths.get(filePath), formattedData, StandardOpenOption.APPEND);
        }
    }
}
