package duke.util;

import duke.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the loading and storing of Tasks to an external text file..
 */

public class Storage {
    String pathname = "data/duke.txt";
    File file = new File(pathname);

    public Storage() { }

    /**
     * If the text file does not exist, create a file.
     * @param f Text file to check existence for.
     * @throws IOException IOException.
     */
    public void checkFileExistence(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Method to update current task list to disk.
     * @param tasks TaskList that represents a collection of Task objects.
     * @throws DukeException DukeException.
     */
    public void update(TaskList tasks) throws DukeException {
        try {
            Path path = Paths.get(pathname);
            Files.deleteIfExists(path);
            File f = new File(pathname);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(pathname, true);

            for (int i = 1; i < tasks.getTasklist().size() + 1; i++) {
                Task t = tasks.get(i - 1);
                String text = t.fileFormat() + "\n";
                fw.write(text);
            }
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Can't update file!");
        }
    }


    /**
     * Method to load list of tasks from disk and make a new TaskList object.
     * @return TaskList that represents a collection of Task objects.
     * @throws DukeException DukeException.
     */
    public TaskList load() throws DukeException {
        try {
            checkFileExistence(file);
            Scanner s = new Scanner(file);
            ArrayList<Task> storedTasks = new ArrayList<>();

            while (s.hasNext()) {
                Task t = processTaskToLoad(s.nextLine());
                storedTasks.add(t);
            }

            return new TaskList(storedTasks);

        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        } catch (IOException e) {
            throw new DukeException("File not found!");
        }
    }

    /**
     * Checks if a Task object has been marked as done.
     * @param s the character that denotes whether a Task has been completed.
     * @param task the task object that is being checked.
     */
    public void checkIfDone(String s, Task task) {
        assert s.equals("1") || s.equals("0");
        if (s.equals("1")) {
            task.markAsDone();
        }
    }

    /**
     * Convert the task from String format in the disk to a Task object.
     * @param line string of input from the disk in hard disk format.
     * @return a Task.
     * @throws DukeException DukeException.
     */
    private Task processTaskToLoad(String line) throws DukeException {
        assert line.length() > 0;
        String[] data = line.split(" , ");
        Task t = new Task("null");

        if (data[0].equals("T")) {
            t = new Todo(data[2]);
            checkIfDone(data[1], t);
        }

        if (data[0].equals("D")) {
            t = new Deadline(data[2], data[3]);
            checkIfDone(data[1], t);
        }

        if (data[0].equals("E")) {
            t = new Event(data[2], data[3]);
            checkIfDone(data[1], t);
        }

        return t;
    }
}
