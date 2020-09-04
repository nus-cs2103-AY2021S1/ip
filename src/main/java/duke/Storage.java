package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Storage class that interacts with the .txt file to store and retrieve data.
 */
public class Storage {
    static final String HOME = System.getProperty("user.home");
    static final Path DIR = Paths.get(HOME, "data");
    static final Path PATH = Paths.get(HOME, "data", "iPStore.txt");
    static final String IO_MESSAGE = "Sorry! There was an IOException! Initialising with an empty Tasklist!";
    static final String INVALID_MESSAGE = "Sorry! There was an error in reading your data! "
            +
            "Initialising with a semi-complete Tasklist!";

    /**
     * Constructor for Storage class.
     */
    public Storage() {
    }

    /**
     * Combines all tasks into 1 long string separated by newlines.
     *
     * @param items List of tasks.
     * @return A string that can be stored in the .txt file.
     */
    String combineAllTasks(ArrayList<Task> items) {
        String res = "";
        for (Task item : items) {
            res += String.format("%s", item.saveString());
            res += "\n";
        }
        return res;
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
        if (info.length < 3) {
            throw new InvalidDataException();
        }
        boolean isComplete = info[1].equals("1");
        switch (info[0]) {
        case "T":
            return new Todo(info[2], isComplete);
        case "D":
            return new Deadline(info[2], isComplete, info[3]);
        case "E":
            return new Event(info[2], isComplete, info[3]);
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
    public ArrayList<Task> readData() {
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
                e.printStackTrace();
                Ui.addLine(this.IO_MESSAGE);
            } catch (InvalidTypeException e) {
                System.out.println(e.toString());
                Ui.addLine(this.INVALID_MESSAGE);
            } catch (InvalidDataException e) {
                System.out.println(e.toString());
                Ui.addLine(this.INVALID_MESSAGE);
            }
        }

        return res;
    }
}
