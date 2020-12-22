package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exceptions.DukeException;
import duke.exceptions.FileLoadFailException;
import duke.exceptions.StorageUpdateFailException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Represents a storage system to load tasks from file and append/overwrite tasks.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Scans through file at specified filePath and make use of the information to initialise tasks arraylist.
     * If directory or file does not exist, it creates the necessary directories and file.
     *
     * @return A list of tasks found in the file at specified filePath.
     * @throws DukeException if unable to load file.
     */
    public List<Task> load() throws DukeException {
        try {
            assert !filePath.isEmpty() : "No filepath specified.";
            List<Task> tasks = new ArrayList<>();
            int numTasks = 0;
            File f = new File(filePath); // create a File for the given file path

            if (!f.exists()) {
                File directory = new File("data");
                directory.mkdir(); // creates the directory if it does not exist

                File file = new File(filePath);
                file.createNewFile();
            }

            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            // go through file contents and initialise the tasks arraylist
            while (s.hasNext()) {
                String currString = s.nextLine();
                String[] currStringArray = currString.split(" \\| ");
                String taskIdentifier = currStringArray[0];
                boolean isDone = currStringArray[1].equals("1");

                if (taskIdentifier.equals("T")) {
                    tasks.add(numTasks, new ToDo(currStringArray[2], isDone));
                } else if (taskIdentifier.equals("D")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime byLocalDate = LocalDateTime.parse(currStringArray[3], formatter);

                    tasks.add(numTasks, new Deadline(currStringArray[2], byLocalDate, isDone));
                } else if (taskIdentifier.equals("E")) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    LocalDateTime timingLocalDate = LocalDateTime.parse(currStringArray[3], formatter);

                    tasks.add(numTasks, new Event(currStringArray[2], timingLocalDate, isDone));
                }
                numTasks++;
            }
            return tasks;
        } catch (IOException e) {
            throw new FileLoadFailException();
        }
    }

    /**
     * Adds text to the file at specified filepath.
     *
     * @param textToAppend Text to be added to the file.
     * @throws DukeException if unable to append a line to the file.
     */
    public void appendToFile(String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            throw new StorageUpdateFailException();
        }

    }

    /**
     * Rewrites the entire file based on the tasks arraylist, looping through each task and formatting it.
     *
     * @param tasks
     * @throws DukeException
     */
    public void overwriteFile(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            String tasksList = "";

            for (int i = 0; i < tasks.size(); i++) {
                if (i == 0) {
                    tasksList = tasks.get(i).toTxtFileFormat();
                } else {
                    tasksList = tasksList + "\n" + tasks.get(i).toTxtFileFormat();
                }
            }

            fw.write(tasksList);
            fw.close();
        } catch (IOException e) {
            throw new StorageUpdateFailException();
        }

    }
}
