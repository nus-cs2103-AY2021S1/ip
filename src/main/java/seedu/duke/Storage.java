package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeStorageException;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.ToDo;
import seedu.duke.ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Represents a storage space for the program output.
 */

public class Storage {

    public static final String DEFAULT_STORAGE_FILEPATH = "duke.txt";
    public static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy kkmm", Locale.ENGLISH);
    public static String TICK = "\u2713";
    public static String CROSS = "\u2718";

    public final String path;

    public Storage() {
        this.path = DEFAULT_STORAGE_FILEPATH;
    }


    /**
     * Loads the tasks from the textfile so that Duke can access the tasks
     * @return List containing the tasks
     * @throws IOException When the path is incorrect
     * @throws DukeException When the text file contains lines that are not supposed to be there.
     */
    public ArrayList<Task> load() throws IOException, DukeException {
        File f = new File(this.path);
        f.createNewFile();
        assert f.exists() : "duke.txt should have been created or already exists";

        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> ls = new ArrayList<>();
        assert ls.size() == 0: "Should be a new list of tasks.";

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr = line.split("\\*");
            String task = arr[0];
            assert task.equals("E") || task.equals("T") || task.equals("D") :
                    "Should have a prefix before each task in the text file.";
            switch (task) {
                case "E": { // case where the task is an event
                    boolean status = arr[1].equals(TICK);
                    String todo = arr[2];
                    String[] times = arr[3].split(" to ");
                    LocalDateTime startTime = LocalDateTime.parse(times[0], FORMATTER);
                    LocalDateTime endTime = LocalDateTime.parse(times[1], FORMATTER);
                    ls.add(new Event(todo, startTime, endTime, status));
                    break;
                }
                case "T": {
                    boolean status = arr[1].equals(TICK);
                    String todo = arr[2];
                    ls.add(new ToDo(todo, status));
                    break;
                }
                case "D": {
                    boolean status = arr[1].equals(TICK);
                    String todo = arr[2];
                    LocalDateTime deadline = LocalDateTime.parse(arr[3], FORMATTER);
                    ls.add(new Deadline(todo, deadline, status));
                    break;
                }
                default: {
                    throw new DukeStorageException("Gee somehow your duke text file got corrupted. Seriously?");
                }
            }
        }
        return ls;
    }

    /**
     * Writes the tasks from the current running Duke and saves it in a text file
     * @param ls The list of tasks of the current Duke.
     * @param ui The Ui of the current Duke.
     */
    public void save(TaskList ls, Ui ui) {
        try {
            String tasks = "";

            for (Task t : ls.getList()) {
                String check = "";
                if (t.isComplete()) {
                    check = TICK;
                } else {
                    check = CROSS;
                }
                String toAdd = t.getType() + "*" + check + "*" + t.toString();
                String addition = "";
                if (t.getTime() == null) {
                    addition = "\n";
                } else if (t.getType().equals("D")) {
                    addition = "*" + t.getTime().format(FORMATTER) + "\n";
                } else {
                    addition = "*" + t.getTime().format(FORMATTER) + " to "
                            + t.getEndTime().format(FORMATTER) + "\n";
                }
                tasks = tasks + toAdd + addition;
            }
            FileWriter fw = new FileWriter(this.path);

            fw.write(tasks + System.lineSeparator());

            fw.close();
        } catch (IOException e) {
            ui.showError("Whoops! Some kind of error :/ see here: " + e.getMessage());
        }

    }


}
