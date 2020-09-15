package duke.backend;

import duke.DukeException;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

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

    private File file;

    public Storage(String filepath) {
        file = new File(filepath);
    }

    /**
     * Reads the user's saved file and returns a list of all the tasks found in the file.
     * @return A list of task.
     * @throws FileNotFoundException When no file is found.
     * @throws DukeException When no file is found.
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, DukeException {
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
        FileWriter writer = new FileWriter(file);
        writer.write(tasks.toString());
        writer.close();
    }

    private static Task getTask(String line) throws DukeException {
        String[] parsed = line.split(RIGHT_ICON_BRACKET, 3);
        String type = parsed[0].substring(1);
        String status = parsed[1].substring(1);
        boolean isDone = (status.equals("\u2713")); // \u2713 is tick, \u2718 is cross.
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
