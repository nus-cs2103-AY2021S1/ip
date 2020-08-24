package duke;

import exception.DukeException;
import exception.DukeFileException;
import exception.DukeIOException;
import exception.InvalidInputException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

import java.io.*;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Storage Class which handles reading and writing tasks from txt file
 */

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        File file = readFile();
        return parseFile(file);
    }

    /**
     * Reads the file from the file at location filePath
     * @return File located at location filePath
     * @throws DukeException Exception when there are errors in file reading
     */
    public File readFile() throws DukeException {
        try {
            String FOLDERPATH = "data";
            File directory = new File(FOLDERPATH);
            if (!directory.isDirectory()) {
                // create Directory data
                File folder = new File(FOLDERPATH);
                if (!folder.mkdir()) {
                    throw new DukeFileException();
                }
            }

            File file = new File(this.filePath);
            if (file.exists() ||file.createNewFile()) {
                return file;
            } else {
                throw new DukeFileException();
            }

        }  catch (IOException e1) {
            throw new DukeIOException();
        }
    }


    /**
     * ReadLine takes in a String and returns a Task
     * @param line String containing information about a Task
     * @return Task which corresponds to the String line
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
        } else if (deadlineMatcher.find()){
            return new DeadlineTask(deadlineMatcher.group(2), Integer.parseInt(deadlineMatcher.group(1)), LocalDateTime.parse(deadlineMatcher.group(3)));
        } else if (eventMatcher.find()) {
            return new EventTask(eventMatcher.group(2), Integer.parseInt(eventMatcher.group(1)), LocalDateTime.parse(eventMatcher.group(3)));
        } else {
            // Formats do not match, input given is invalid
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the contents of the file given into a list of tasks
     * @param file File that needs to parsed
     * @return ArrayList containing Task Objects
     * @throws DukeException Exceptions in parsing the file
     */
    public List<Task> parseFile(File file) throws DukeException {
        List<Task> result = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            //T | 1 | read book

            while ((line = reader.readLine()) != null) {
                Task task = readLine(line);
                if (task != null) {
                    result.add(task);
                }
            }
            return result;
        } catch(FileNotFoundException e) {
            throw new DukeFileException();
        } catch (IOException e2) {
            throw new DukeIOException();
        }
    }

    /**
     * Saves the contents of the taskList into a txt file
     * @param taskList ArrayList containing tasks to be saved
     * @throws DukeException Exceptions in saving tasks
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        try {
            File file = readFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task: taskList.getTaskList()) {
                if (task instanceof TodoTask) {
                    writer.write(String.format("T | %s | %s\n", task.getHasCompleted(), task.getName()));
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    writer.write(String.format("D | %s | %s | %s\n", deadlineTask.getHasCompleted(), deadlineTask.getName(), deadlineTask.getDeadline()));
                } else if (task instanceof EventTask) {
                    EventTask eventTask = (EventTask) task;
                    writer.write(String.format("E | %s | %s | %s\n", eventTask.getHasCompleted(), eventTask.getName(), eventTask.getTime()));
                }
            }
            writer.close();
        } catch(IOException e) {
            throw new DukeIOException();
        }
    }

}
