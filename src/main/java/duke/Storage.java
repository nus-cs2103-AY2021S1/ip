package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * The Storage class deals with handling the writing and reading of data to the file specified
 */

public class Storage {
    private final File dataFile;
    @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "CheckStyle"})
    private final String FilePath;

    /**
     * Initializes a storage object
     *
     * @param filePath String representing the filepath to load the file
     */
    public Storage(String filePath) {
        this.FilePath = filePath;
        dataFile = new File(filePath);
        try {
            if (dataFile.createNewFile()) {
                System.out.println("data file has been created");
            }
        } catch (IOException err) {
            System.out.println("error opening file");
        }
    }

    /**
     * Appends task to the file
     *
     * @param task String representing task to be appended
     */

    public void appendFile (String task) {
        try {
            FileWriter fw = new FileWriter(FilePath, true);
            fw.write(task);
            fw.close();
        } catch (IOException err) {
            System.out.println("error writing " + task + " to file storage");
        }
    }

    /**
     * Overwrites the file with data from the list given
     *
     * @param list representing the list of tasks
     */
    public void overwriteFile (ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(FilePath);
            for (Task currentTask : list) {
                String done = currentTask.isDone() ? "1" : "0";
                if (currentTask instanceof Event) {
                    fw.write("E | " + done + " | " + currentTask.getTaskName() + " | "
                            + ((Event) currentTask).getDate() + "\n");
                } else if (currentTask instanceof Deadline) {
                    fw.write("D | " + done + " | " + currentTask.getTaskName() + " | "
                            + ((Deadline) currentTask).getDate() + "\n");
                } else if (currentTask instanceof Todo) {
                    fw.write("T | " + done + " | " + currentTask.getTaskName() + "\n");
                }
            }
            fw.close();
        } catch (IOException err) {
            System.out.println("error overwriting file.");
        }
    }

    /**
     * Loads the file into an array list.
     *
     * @return ArrayList of type Task stored in the file
     * @throws FileNotFoundException file not found
     * @throws DukeException error with files
     */
    public ArrayList<Task> load() throws FileNotFoundException, DukeException {
        ArrayList<Task> toReturn = new ArrayList<>();
        Scanner reader = new Scanner(dataFile);
        while (reader.hasNext()) {
            String currentTask = reader.nextLine();
            String[] splits = currentTask.split(" \\| ");
            boolean done = Integer.parseInt(splits[1]) == 1;
            String type = splits[0];
            String task = splits[2];
            switch (type) {
            case ("T"):
                Task todoTask = new Todo(task);
                if (done) {
                    todoTask.checkOff();
                }
                toReturn.add(todoTask);
                break;
            case ("D"):
                String date = splits[3];
                Deadline deadlineTask = new Deadline(task, date);
                if (done) {
                    deadlineTask.checkOff();
                }
                toReturn.add(deadlineTask);
                break;
            case ("E"):
                String day = splits[3];
                Event eventTask = new Event(task, day);
                if (done) {
                    eventTask.checkOff();
                }
                toReturn.add(eventTask);
                break;
            default:
                System.out.println("this should not occur, error in loading of files");
                break;
            }
        }
        return toReturn;
    }
}
