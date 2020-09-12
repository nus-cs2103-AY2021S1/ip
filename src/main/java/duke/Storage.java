package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.DukeException;
import exception.DukeFileException;
import exception.DukeIoException;
import exception.InvalidInputException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

/**
 * Storage Class which handles reading and writing tasks from txt file
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that returns a List of Task objects read from a file.
     *
     * @return List containing objects of Task type.
     * @throws DukeException Exception when there is error in loading tasks.
     */
    public List<Task> load() throws DukeException {
        File file = readFile();
        assert file != null;
        return parseFile(file);
    }

    /**
     * Reads the file from the file at location filePath.
     *
     * @return File located at location filePath.
     * @throws DukeException Exception when there are errors in file reading.
     */
    public File readFile() throws DukeException {
        try {
            File data = new File(filePath);
            data.getParentFile().mkdirs();
            data.createNewFile();
            return data;
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }


    /**
     * ReadLine takes in a String and returns a Task.
     *
     * @param line String containing information about a Task.
     * @return Task which corresponds to the String line.
     * @throws DukeException Exception when there are errors in reading the line.
     */
    public Task readLine(String line) throws DukeException {
        Pattern todoPattern = Pattern.compile("T \\| ([01]) \\| (.+)");
        Pattern deadlinePattern = Pattern.compile("D \\| ([01]) \\| (.+?) \\| (.+)");
        Pattern eventPattern = Pattern.compile("E \\| ([01]) \\| (.+?) \\| (.+)");
        Matcher todoMatcher = todoPattern.matcher(line);
        Matcher deadlineMatcher = deadlinePattern.matcher(line);
        Matcher eventMatcher = eventPattern.matcher(line);

        if (todoMatcher.find()) {
            return new TodoTask(todoMatcher.group(2), Integer.parseInt(todoMatcher.group(1)));
        } else if (deadlineMatcher.find()) {
            return new DeadlineTask(
                    deadlineMatcher.group(2),
                    Integer.parseInt(deadlineMatcher.group(1)),
                    LocalDateTime.parse(deadlineMatcher.group(3)));
        } else if (eventMatcher.find()) {
            return new EventTask(
                    eventMatcher.group(2),
                    Integer.parseInt(eventMatcher.group(1)),
                    LocalDateTime.parse(eventMatcher.group(3)));
        } else {
            // Formats do not match, input given is invalid
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the contents of the file given into a list of tasks.
     *
     * @param file File that needs to parsed.
     * @return ArrayList containing Task Objects.
     * @throws DukeException Exceptions in parsing the file.
     */
    public List<Task> parseFile(File file) throws DukeException {
        List<Task> results = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            // T | 1 | read book

            while (line != null) {
                Task task = readLine(line);
                if (task != null) {
                    results.add(task);
                }
                line = reader.readLine();
            }
            return results;
        } catch (FileNotFoundException e) {
            throw new DukeFileException();
        } catch (IOException e2) {
            throw new DukeIoException();
        }

    }

    /**
     * Saves the contents of the taskList into a txt file.
     *
     * @param taskList ArrayList containing tasks to be saved.
     * @throws DukeException Exceptions in saving tasks.
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            File file = readFile();
            assert file != null;
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : taskList.getTasks()) {
                writer.write(task.getFormattedString());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }
}
