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
    protected File dataFile;
    @SuppressWarnings({"checkstyle:AbbreviationAsWordInName", "CheckStyle"})
    private final String filePath;

    /**
     * Initializes a storage object
     *
     * @param filePath String representing the filepath to load the file
     */
    public Storage(String filePath, String dataPath) {
        this.filePath = filePath;
        dataFile = new File(filePath);
        try {
            if (dataFile.exists() == false) {
                File createPath = new File(dataPath);
                createPath.mkdir();
                dataFile.createNewFile();
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
        //@@author yongmingyang-reused
        //Reused from https://www.tutorialspoint.com/java/java_filewriter_class.htm with modifications
        try {
            FileWriter fw = new FileWriter(filePath, true);
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
            FileWriter fw = new FileWriter(filePath);
            for (Task currentTask : list) {
                fw.write(identifyTask(currentTask));
            }
            fw.close();
        } catch (IOException err) {
            System.out.println("error overwriting file.");
        } catch (DukeException err) {
            System.out.println("error interpreting task");
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
            String frequency = "";
            boolean isRepetitive = false;
            if (splits.length > 4) {
                frequency = splits[4].toLowerCase();
                isRepetitive = !frequency.equals("");
            }
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
                Deadline deadlineTask = isRepetitive
                                            ? new Deadline(task, date, frequency)
                                            : new Deadline(task, date);
                if (done) {
                    deadlineTask.checkOff();
                }
                toReturn.add(deadlineTask);
                break;
            case ("E"):
                String day = splits[3];
                Event eventTask = isRepetitive
                                    ? new Event(task, day, frequency)
                                    : new Event(task, day);
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
    /**
     * Identifies the type of the current task
     * @param currentTask the current task to identify
     * @return String to write into the file
     * @throws DukeException when current task does not fit any description
     */
    private String identifyTask(Task currentTask) throws DukeException {
        String done = currentTask.isDone() ? "1" : "0";
        Boolean isRepetitive = currentTask.getIsRepetitive();
        String frequency = isRepetitive ? currentTask.getFrequency() : "";
        if (currentTask instanceof Event) {
            return "E | " + done + " | " + currentTask.getTaskName() + " | "
                    + ((Event) currentTask).getDate() + " | " + frequency + "\n";
        } else if (currentTask instanceof Deadline) {
            return "D | " + done + " | " + currentTask.getTaskName() + " | "
                    + ((Deadline) currentTask).getDate() + " | " + frequency + "\n";
        } else if (currentTask instanceof Todo) {
            return "T | " + done + " | " + currentTask.getTaskName() + "\n";
        } else {
            throw new DukeException("error interpreting task");
        }
    }
}

