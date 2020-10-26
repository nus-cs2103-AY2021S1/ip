package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the program storage, which can load and save tasks.
 */
public class Storage {

    private final String filePath;

    /**
     * Initializes a newly created Storage with a file path.
     *
     * @param filePath path of the saved tasks file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks into a task list.
     *
     * @return list of tasks.
     * @throws DukeException if there are any I/O issues.
     */
    public List<Task> load() throws DukeException {

        List<Task> tasks = new ArrayList<>();
        assert this.filePath != null;

        try {
            int lastSlash = this.filePath.lastIndexOf('/');
            boolean isInDirectory = lastSlash != -1;

            if (isInDirectory) {
                String directoryPath = filePath.substring(0, lastSlash);
                Files.createDirectories(Paths.get("./" + directoryPath));
            }

            File file = new File(this.filePath);
            boolean hasExistingFile = !file.createNewFile();

            if (hasExistingFile) {
                loadFromFile(tasks);
            }
        } catch (IOException ex) {
            throw new DukeException("FATAL: " + ex.getMessage());
        }
        return tasks;
    }

    /**
     * Loads tasks from a file to a list.
     *
     * @param tasks list of tasks.
     * @throws DukeException if there are any I/O issues.
     */
    private void loadFromFile(List<Task> tasks) throws DukeException {

        assert this.filePath != null;

        try {
            File f = new File(this.filePath);
            Scanner s = new Scanner(f);
            int lineCounter = 0;

            while (s.hasNext()) {
                lineCounter++;
                String line = s.nextLine();
                String[] attributes = line.split(" \\| ");

                checkFormat(attributes, lineCounter);

                String type = attributes[0];
                String doneStatus = attributes[1];
                String description = attributes[2];
                String meta;
                boolean isDone = doneStatus.equals("V");

                switch (type) {
                case "T":
                    tasks.add(new Todo(description, isDone));
                    break;
                case "D":
                    meta = attributes[3];
                    tasks.add(createTask("deadline", description, meta, isDone));
                    break;
                default:
                    meta = attributes[3];
                    tasks.add(createTask("event", description, meta, isDone));
                    break;
                }
            }
        } catch (DateTimeParseException ex) {
            throw new DukeException("INVALID DATE/TIME FORMAT DETECTED");
        } catch (FileNotFoundException ex) {
            throw new DukeException(ex.getMessage());
        }
    }

    /**
     * Processes a string from the save file to a task.
     *
     * @param type type of task.
     * @param description description of task.
     * @param meta metadata of task.
     * @param isDone whether the task is done.
     * @return task derived from string.
     */
    private Task createTask(String type, String description, String meta, boolean isDone) {

        assert type != null && description != null && meta != null;
        boolean hasTime = meta.contains(" ");

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        if (hasTime) {
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HHmm");
            String dateAsString = meta.substring(0, meta.indexOf(' '));
            String timeAsString = meta.substring(meta.indexOf(' ') + 1);

            LocalDate date = LocalDate.parse(dateAsString, dateFormat);
            LocalTime time = LocalTime.parse(timeAsString, timeFormat);

            return type.equals("deadline")
                    ? new Deadline(description, date, time, isDone)
                    : new Event(description, date, time, isDone);
        } else {
            assert !meta.contains(" ");
            LocalDate date = LocalDate.parse(meta, dateFormat);
            return type.equals("deadline")
                    ? new Deadline(description, date, isDone)
                    : new Event(description, date, isDone);
        }
    }

    /**
     * Checks for any formatting issues from the save file.
     *
     * @param attr attributes of task.
     * @param lineCounter current line number.
     * @throws DukeException if there are any formatting issues.
     */
    private void checkFormat(String[] attr, int lineCounter) throws DukeException {

        String type = attr[0];
        String doneStatus = attr[1];

        boolean hasIncompleteAttributes = attr.length < 3;
        boolean hasNoMeta = attr.length != 4;
        boolean hasInvalidDoneStatus = !doneStatus.equals("V") && !doneStatus.equals("X");
        boolean hasNoTimestamp = (type.equals("D") || type.equals("E")) && hasNoMeta;
        boolean hasInvalidTaskType = !type.equals("D") && !type.equals("E") && !type.equals("T");

        boolean hasInvalidType = hasInvalidTaskType || hasInvalidDoneStatus;
        boolean isIncomplete = hasIncompleteAttributes || hasNoTimestamp;
        boolean isInvalid = hasInvalidType || isIncomplete;

        if (isInvalid) {
            throw new DukeException("INVALID TASK DETECTED: LINE " + lineCounter);
        }
    }

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks list of tasks.
     * @throws DukeException if there are any I/O issues.
     */
    public void save(List<Task> tasks) throws DukeException {

        StringBuilder taskData = new StringBuilder();

        for (Task ts : tasks) {
            taskData.append(ts.formatTask()).append("\n");
        }

        try {
            writeToFile(taskData.toString());
        } catch (IOException ex) {
            throw new DukeException("FATAL: " + ex.getMessage());
        }
    }

    /**
     * Writes a line to a file.
     *
     * @param textToAdd text to be written.
     * @throws IOException if there are any I/O issues.
     */
    private void writeToFile(String textToAdd) throws IOException {

        assert textToAdd != null;

        FileWriter fw = new FileWriter(this.filePath);
        fw.write(textToAdd);
        fw.close();
    }
}
