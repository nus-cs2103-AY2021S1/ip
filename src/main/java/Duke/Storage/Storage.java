package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import duke.model.Deadline;
import duke.model.DurationTask;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;

/**
 * Encapsulates the operations related to the I/O of the log file.
 */
public class Storage {
    private final String pathname;
    private List<Task> data = new ArrayList<>();

    /**
     * Constructs a storage object.
     * @param path the file path.
     */
    public Storage(String path) {
        this.pathname = path;
    }

    /**
     * Reads data from the log file.
     * @return data that represented by a list of Task objects.
     * @throws IOException if cannot read files.
     */
    public List<Task> readFile() throws IOException {
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line;
        line = br.readLine();
        while (line != null) {
            char type = line.charAt(8);
            boolean isDone = line.charAt(11) == 'Y';
            switch (type) {
            case 'T':
                readTask(line, isDone);
                break;
            case 'U':
                readDurationTask(line, isDone);
                break;
            case 'D':
                readDeadline(line, isDone);
                break;
            case 'E':
                readEvent(line, isDone);
                break;
            default:
                throw new IOException();
            }
            line = br.readLine();
        }
        return this.data;
    }

    /**
     * Writes the data into the log file.
     * @param data the data that will be stored in the file.
     * @throws FileNotFoundException if cannot write into file.
     */
    public void writeFile(List<Task> data) throws FileNotFoundException {
        final PrintStream oldStdout = System.out;
        PrintStream ps = new PrintStream(pathname);
        System.setOut(ps);
        for (int i = 0; i < data.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, data.get(i).toString());
        }
        System.setOut(oldStdout);
    }

    /**
     * Converts the words of month into corresponding numbers.
     * @param month word representation of months.
     * @return number representation of months.
     * @throws IOException if encounter unrecognised character.
     */
    public static int getMonth(String month) throws IOException {
        switch (month) {
        case "JAN":
            return 1;
        case "FEB":
            return 2;
        case "MAR":
            return 3;
        case "APR":
            return 4;
        case "MAY":
            return 5;
        case "JUN":
            return 6;
        case "JUL":
            return 7;
        case "AUG":
            return 8;
        case "SEP":
            return 9;
        case "OCT":
            return 10;
        case "NOV":
            return 11;
        case "DEC":
            return 12;
        default:
            throw new IOException();
        }
    }

    /**
     * reads in a Task.
     * @param line contains information for Task.
     * @param isDone indicates whether it is done or not.
     */
    private void readTask(String line, boolean isDone) {
        Todo t = new Todo(line.substring(14), isDone);
        data.add(t);
    }

    /**
     * reads in a DurationTask.
     * @param line contains information for DurationTask.
     * @param isDone indicates whether it is done or not.
     */
    private void readDurationTask(String line, boolean isDone) {
        String[] parts = line.substring(5).split(" ");
        String description = "";
        int length = parts.length;
        for (int i = 1; i < length - 2; i++) {
            description += parts[i] + " ";
        }
        DurationTask u = new DurationTask(description,
                Integer.parseInt(parts[parts.length - 2].substring(1)), isDone);
        data.add(u);
    }

    /**
     * reads in a Deadline task.
     * @param line contains information for Deadline task.
     * @param isDone indicates whether it is done or not.
     * @throws IOException if encounter unrecognised character.
     */
    public void readDeadline(String line, boolean isDone) throws IOException {
        String[] parts = line.substring(5).split(" ");
        String description = "";
        int length = parts.length;
        int year = Integer.parseInt(parts[length - 2]);
        int month = getMonth(parts[length - 4]);
        int day = Integer.parseInt(parts[length - 3]);
        int hour = Integer.parseInt(parts[length - 1].split(":")[0]);
        int minute = Integer.parseInt(parts[length - 1].split(":")[1].substring(
                0, parts[length - 1].split(":")[1].length() - 1));

        for (int i = 1; i < length - 5; i++) {
            description += parts[i] + " ";
        }
        Deadline d = new Deadline(description, LocalDateTime.of(year, month, day, hour, minute), isDone);
        data.add(d);
    }

    /**
     * reads in an Event task.
     * @param line contains information for Event task.
     * @param isDone indicates whether it is done or not.
     * @throws IOException if encounter unrecognised character.
     */
    public void readEvent(String line, boolean isDone) throws IOException {
        String[] parts = line.substring(5).split(" ");
        String description = "";
        int length = parts.length;
        int year = Integer.parseInt(parts[length - 2]);
        int month = getMonth(parts[length - 4]);
        int day = Integer.parseInt(parts[length - 3]);
        int hour = Integer.parseInt(parts[length - 1].split(":")[0]);
        int minute = Integer.parseInt(parts[length - 1].split(":")[1].substring(
                0, parts[length - 1].split(":")[1].length() - 1));

        for (int i = 1; i < length - 5; i++) {
            description += parts[i] + " ";
        }
        Event e = new Event(description, LocalDateTime.of(year, month, day, hour, minute), isDone);
        data.add(e);
    }
}
