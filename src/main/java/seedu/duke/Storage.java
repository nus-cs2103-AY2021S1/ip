package seedu.duke;

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

    public final String path;

    public Storage() {
        this.path = DEFAULT_STORAGE_FILEPATH;
    }

    public Storage(String filepath) {
        this.path = filepath;
    }

    /**
     * Loads the tasks from the textfile so that Duke can access the tasks
     * @return List containing the tasks
     * @throws IOException When the path is incorrect
     */
    public ArrayList<Task> load() throws IOException {
        File f = new File(this.path);
        f.createNewFile();
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> ls = new ArrayList<>();

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] arr = line.split("\\*");
            String task = arr[0];
            if (task.equals("E")) { // case where the task is an event
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                LocalDateTime deadline = LocalDateTime.parse(arr[3], FORMATTER);
                ls.add(new Event(todo, deadline, status));
            } else if (task.equals("T")) {
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                ls.add(new ToDo(todo, status));
            } else {
                boolean status = arr[1].equals("\u2713");
                String todo = arr[2];
                LocalDateTime deadline = LocalDateTime.parse(arr[3], FORMATTER);
                ls.add(new Deadline(todo, deadline, status));
            }
        }
        return ls;
    }

    /**
     * Writes the tasks from the current running Duke and saves it in a text file
     * @param textToAdd The string version of the task to be added
     * @throws IOException When the path is incorrect
     */
    public void save(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.path);
        fw.write(textToAdd + System.lineSeparator());
        fw.close();
    }


}
