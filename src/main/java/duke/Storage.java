package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.DukeFileNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Storage deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected ArrayList<Task> list;
    protected File file;
    protected String fileName;

    /**
     * Storage constructor.
     */
    public Storage() {
        this.list = new ArrayList<>();
    }

    /**
     * Loads the saved tasks. On the case that the file is not found,
     * this will create a directory and a text file.
     *
     * @return An ArrayList of tasks.
     * @throws DukeFileNotFoundException Checks if the file is deleted or corrupted halfway.
     */
    ArrayList<Task> load() throws DukeFileNotFoundException {

        // Creates a string path that is universal across multiple operating systems.
        String home = System.getProperty("user.home");
        String dataPath = java.nio.file.Paths.get(home, "data").toString();
        fileName = java.nio.file.Paths.get(dataPath, "duke.txt").toString();

        // Check and load/create the file accordingly
        File dataDirectory = new File(dataPath);
        if (dataDirectory.mkdir()) {
            createFile(fileName);
        } else {
            this.file = new File(fileName);
        }

        try {
            // Scanning the document
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String currentLine = sc.nextLine();
                String[] arrOfString = currentLine.split(" \\| ");

                // Represents if a task is done
                Integer num = Integer.parseInt(arrOfString[1]);

                // Converts the num representation to a boolean
                Boolean isDone = num.equals(1);
                String title = arrOfString[0];
                String description = arrOfString[2];

                // Creates the tasks accordingly
                if (title.equals("T")) {
                    this.list.add(new Todo(description, isDone));
                } else if (title.equals("D")) {
                    this.list.add(new Deadline(description, isDone, Parser.parseDateTime(arrOfString[3])));
                } else if (title.equals("E")) {
                    this.list.add(new Event(description, isDone, Parser.parseDateTime(arrOfString[3])));
                }
            }
            return this.list;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException();
        }
    }

    /**
     * Creates the text file.
     *
     * @param path The path where the duke text file will be created.
     * @throws DukeFileNotFoundException Checks if there is any error after saving.
     */
    void createFile(String path) throws DukeFileNotFoundException {
        File dukeFile = new File(path);
        try {
            if (dukeFile.createNewFile()) {
                System.out.println("     File created: " + dukeFile.getName());
                this.file = dukeFile;
            } else {
                System.out.println("     File already exists.");
            }
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }

    /**
     * Saves the tasks into the text file into a proper format.
     *
     * @param tasks List of tasks.
     * @throws DukeException Checks if the file is deleted or corrupted halfway.
     */
    public void save(List<Task> tasks) throws DukeException {
        DateTimeFormatter style = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try {
            FileWriter myWriter = new FileWriter(fileName);
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    myWriter.write("T | "
                            + (task.isDone() ? "1 | " : "0 | ")
                            + task.getDescription());
                } else if (task instanceof Deadline) {
                    myWriter.write("D | "
                            + (task.isDone() ? "1 | " : "0 | ")
                            + task.getDescription() + " | "
                            + ((Deadline) task).getDate().format(style));
                } else {
                    myWriter.write("E | "
                            + (task.isDone() ? "1 | " : "0 | ")
                            + task.getDescription() + " | "
                            + ((Event) task).getDate().format(style));
                }
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeFileNotFoundException();
        }
    }
}
