package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Implements the loading and storing of Tasks to an external text file.
 */

public class Storage {
    String filePath = Duke.filePath;
    File file = new File("data/duke.txt");

    public Storage() {

    }

    /**
     * If the text file does not exist, create a file
     * @param f Text file to check existence for
     * @throws IOException
     */
    public void checkFileExistence(File f) throws IOException {
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Method to update current task list to disk
     * @param tasks TaskList that represents a collection of Task objects
     * @throws DukeException
     */
    public void update(TaskList tasks) throws DukeException {

        try {
            checkFileExistence(this.file);
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 1; i < tasks.getTasklist().size() + 1; i++) {
                Task t = tasks.get(i - 1);
                String text = t.fileFormat() + "\n";
                bw.write(text);
            }
            bw.close();

        } catch (IOException e) {
            throw new DukeException("Can't update file!");
        }
    }


    /**
     * Method to load list of tasks from disk and make a new TaskList object
     * @return TaskList that represents a collection of Task objects
     * @throws DukeException
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

    public void checkIfDone(String s, Task task) {
        if (s.equals("1")) {
            task.markAsDone();
        }
    }

    /**
     * Convert the task from String format in the disk to a Task object
     * @param line
     * @return a Task
     * @throws DukeException
     */
    private Task processTaskToLoad(String line) throws DukeException {
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
