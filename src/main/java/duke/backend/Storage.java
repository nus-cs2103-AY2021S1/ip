package duke.backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Responsible for storing the file. A <code>Storage</code> object contains a <code>File</code>
 * object.
 */
public class Storage {
    private static final String RIGHT_ICON_BRACKET = "]";
    private static final String TIME_SEPARATOR = " - ";

    private static final String TODO_ICON = "T";
    private static final String DEADLINE_ICON = "D";
    private static final String EVENT_ICON = "E";

    private Path path;

    /**
     * Constructs Storage.
     */
    public Storage(String filePath) throws DukeException {
        String dir = System.getProperty("user.dir");
        path = Path.of(dir, filePath);
        File file = new File(String.valueOf(path));
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Could not create tasks.txt");
        }
    }

    /**
     * Reads the user's saved file and returns a list of all the tasks found in the file.
     * @return A list of task.
     * @throws FileNotFoundException When no file is found.
     * @throws DukeException When no file is found.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, DukeException {
        File file = path.toFile();
        if (!file.exists()) {
            throw new FileNotFoundException("No file found");
        }
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            tasks.add(getTask(scanner.nextLine()));
        }
        return tasks;
    }

    /**
     * Writes what is in the task list into the user's safe file.
     * @param tasks duke.task.TaskList to be read.
     * @throws IOException When no file is found.
     */
    public void writeFile(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(path.toString());
        writer.write(tasks.toString());
        writer.close();
    }

    private static Task getTask(String line) throws DukeException {
        String[] parsed = line.split(RIGHT_ICON_BRACKET, 3);
        String type = parsed[0].substring(1);
        String status = parsed[1].substring(1);
        boolean isDone = (status.equals("1"));
        String body = parsed[2].substring(1);
        if (type.equals(TODO_ICON)) {
            return new Todo(body, isDone);
        }
        if (type.equals(DEADLINE_ICON)) {
            String[] Parsed = body.split(TIME_SEPARATOR, 2);
            String Description = Parsed[0];
            String deadline = Parsed[1];
            LocalDate date = LocalDate.parse(deadline);
            return new Deadline(Description, date, isDone);
        }
        if (type.equals(EVENT_ICON)) {
            String[] Parsed = body.split(TIME_SEPARATOR, 2);
            String Description = Parsed[0];
            String eventTime = Parsed[1];
            LocalDate date = LocalDate.parse(eventTime);
            return new Event(Description, date, isDone);
        }
        throw new DukeException("Error finding task");
    }
}
