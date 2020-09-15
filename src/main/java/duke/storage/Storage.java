package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.InvalidDoneIndicatorException;
import duke.exception.InvalidTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Represents a <code>Storage</code> class that saves and loads
 * Task. A Task is represented here as a String containing the type
 * of Task, the description, whether it is done and the date
 * and time if applicable.
 */
public class Storage {

    /** String path to text file which contains the stored Task */
    private static final String FILE_PATH = "./data/tasks.txt";

    /** Path to text file which contains the stored Task */
    private Path path;

    /**
     * Constructs a <code>Storage</code> object with a default file path.
     */
    public Storage() {
        this(FILE_PATH);
    }

    /**
     * Constructs a <code>Storage</code> object with a specified file path.
     *
     * @param filePath The String path to text file which stores Task.
     */
    private Storage(String filePath) {
        path = Paths.get(filePath);
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    /**
     * Saves the Task into a text file.
     *
     * @param tasks A list of Task.
     */
    public void save(TaskList tasks) {
        try {
            int counter = tasks.getNumberOfTask();
            FileWriter fw = new FileWriter(FILE_PATH);

            for (Task t : tasks.getList()) {
                String taskString = t.toFileString();
                fw.write(taskString);
                counter--;
                if (counter > 0) {
                    fw.write(System.lineSeparator());
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the Task from a text file.
     *
     * @return A list of Task.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            File file = new File(FILE_PATH);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                try {
                    Task t = createTaskFromFile(sc.nextLine());
                    tasks.add(t);
                } catch (DukeException e) {
                    // Skip task creation
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Creates new Task based on  a file String.
     *
     * @param taskDetails String containing a Task details.
     * @return A Task.
     * @throws InvalidDoneIndicatorException  If isDoneString is not "0" or "1".
     * @throws InvalidTaskTypeException  If type is not 'T', 'D' or 'E'.
     */
    private Task createTaskFromFile(String taskDetails) throws InvalidDoneIndicatorException,
            InvalidTaskTypeException {
        String[] taskStringSplit = taskDetails.split(" \\| ", 3);
        String type = taskStringSplit[0];
        String isDoneString = taskStringSplit[1];
        String content = taskStringSplit[2];
        boolean isDone = checkIfDone(isDoneString);

        if (type.equals("T")) {
            return new Todo(content, isDone);
        }
        String[] contentSplit = content.split(" \\| ", 2);
        String description = contentSplit[0];;
        String time = contentSplit[1];

        if (type.equals("D")) {
            return new Deadline(description, isDone, time);
        } else if (type.equals("E")) {
            return new Event(description, isDone, time);
        } else {
            throw new InvalidTaskTypeException();
        }
    }

    /**
     * Return false if String is "0" and true if "1".
     *
     * @param doneIndicator String of "0" or "1".
     * @return boolean value to indicate if a Task is done.
     * @throws InvalidDoneIndicatorException  If doneIndicator is not "0" or "1".
     */
    private boolean checkIfDone(String doneIndicator) throws InvalidDoneIndicatorException {
        if (doneIndicator.equals("1")) {
            return true;
        } else if (doneIndicator.equals("0")) {
            return false;
        } else {
            throw new InvalidDoneIndicatorException();
        }
    }
}
