package duke.misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.exception.InvalidDataException;
import duke.exception.InvalidTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class that interacts with the .txt file to store and retrieve data.
 */
public class Storage {
    static final String HOME = System.getProperty("user.home");
    static final Path DIR = Paths.get(HOME, "data");
    static final Path PATH = Paths.get(HOME, "data", "iPStore.txt");

    /**
     * Constructor for Storage class.
     */
    public Storage() {}

    /**
     * Combines all tasks into 1 long string separated by newlines.
     *
     * @param items List of tasks.
     * @return A string that can be stored in the .txt file.
     */
    String combineAllTasks(ArrayList<Task> items) {
        String res = "";
        for (Task item : items) {
            res += String.format("%s", item.getSaveString());
            res += "\n";
        }
        return res;
    }

    private String[] retrieveTags(String input) {
        return input.split("/");
    }

    /**
     * Converts string to a task.
     *
     * @param str String to be converted.
     * @throws InvalidTypeException In case task type is not one of Event, Deadline, Todo.
     * @throws InvalidDataException In case string is not in the correct format.
     */
    Task stringToTask(String str) throws InvalidTypeException, InvalidDataException {
        String[] info = str.split("\\|");
        int infoLength = info.length;
        if (infoLength < 3) {
            throw new InvalidDataException();
        }
        boolean isComplete = info[1].equals("1");
        boolean hasTags;
        String[] tags = new String[]{};
        switch (info[0]) {
        case Todo.SYMBOL:
            hasTags = infoLength == 4;
            tags = hasTags ? retrieveTags(info[3]) : tags;
            return new Todo(info[2], isComplete, tags);
        case Deadline.SYMBOL:
            hasTags = infoLength == 5;
            tags = hasTags ? retrieveTags(info[4]) : tags;
            return new Deadline(info[2], isComplete, info[3], tags);
        case Event.SYMBOL:
            hasTags = infoLength == 5;
            tags = hasTags ? retrieveTags(info[4]) : tags;
            return new Event(info[2], isComplete, info[3], tags);
        default:
            throw new InvalidTypeException();
        }
    }

    /**
     * Writes data to the .txt file.
     *
     * @param items List of items to write
     * @throws IOException In case there are errors when reading the data.
     */
    public void writeData(ArrayList<Task> items) throws IOException {
        boolean directoryExists = Files.exists(DIR);

        if (!directoryExists) {
            Files.createDirectory(DIR);
        }

        try {
            FileWriter fw = new FileWriter(PATH.toFile());
            PrintWriter pw = new PrintWriter(fw);

            pw.print(combineAllTasks(items));

            pw.close();
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Reads data from .txt file.
     *
     * @return An ArrayList of tasks.
     */
    public ArrayList<Task> readData() throws IOException, InvalidTypeException, InvalidDataException {
        boolean pathExists = Files.exists(PATH);

        ArrayList<Task> res = new ArrayList<>();

        if (pathExists) {
            try {
                FileReader fr = new FileReader(PATH.toFile());
                BufferedReader br = new BufferedReader(fr);
                String str;
                while ((str = br.readLine()) != null) {
                    res.add(stringToTask(str));
                }
            } catch (IOException e) {
                throw e;
            } catch (InvalidTypeException e) {
                throw e;
            } catch (InvalidDataException e) {
                throw e;
            }
        }
        return res;
    }
}
