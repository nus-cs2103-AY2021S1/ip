package duke.storage;

import duke.exception.DukeIOException;
import duke.parser.Parser;
import duke.task.Task;

import java.io.*;

/**
 * Class that encapsulates the saving and reading of tasks.
 */
public class Storage {

    File file;
    TaskList tasks;

    /**
     * Constructor for a Storage object.
     *
     * @param path The path to read/write the file to
     * @throws IOException if it is unable to create the file or if an IO error occurs
     */
    public Storage(String path) throws DukeIOException {
        try {
            tasks = new TaskList();
            file = new File(path);
            file.createNewFile(); // makes new file only if not already existing
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String line;
            // Add all the parsed lines to the ArrayList
            while ((line = csvReader.readLine()) != null) {
                tasks.add(Parser.parseLine(line));
            }
        } catch (IOException ie) {
            throw new DukeIOException("Could not create duke.storage.Storage object due to IO error.");
        }
    }

    /**
     * Adds the given Task to the list of tasks and appends the new task to the data file.
     * @param task the Task to be added
     * @throws IOException if an IOException occurs during writing
     */
    public void add(Task task) throws IOException {
        // TODO: Catch IOException into DukeIOException
        tasks.add(task);
        String taskString = Parser.convertTask(task);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.append(taskString);
        bw.flush();
        bw.close();
    }

    /**
     *
     */

    /**
     * Removes the task at the given index from the TaskList,
     * then clears the existing file and rewrites the contents of the list back to it.
     * @param index The index of the task to be removed
     * @return The deleted task
     * @throws DukeIOException if an IOException occurs during writing.
     * @throws ArrayIndexOutOfBoundsException if the given index is out of range.
     */
    public Task delete(int index) throws DukeIOException, ArrayIndexOutOfBoundsException {
        Task deleted = tasks.remove(index);
        rewrite();
        return deleted;
    }

    /**
     * Marks the task at the given index of the TaskList as completed,
     * then clears the existing file and rewrites the contents of the list back to it.
     * @param index
     * @return
     * @throws DukeIOException
     * @throws ArrayIndexOutOfBoundsException
     */
    public Task complete(int index) throws DukeIOException, ArrayIndexOutOfBoundsException {
        Task completed = tasks.complete(index);
        rewrite();
        return completed;
    }

    /**
     * Clears the existing file and rewrites the contents of the list to the file.
     *
     * @throws IOException if there are issues with the IO operations
     */
    private void rewrite() throws DukeIOException {
        try {
            new FileWriter(file, false).close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks.list()) {
                bw.append(Parser.convertTask(t));
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeIOException("IO Exception when writing to file. ");
        }
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "The list is empty.";
        } else {
            String result = "";
            for (Task t : tasks.list()) {
                result = result.concat(t.toString()).concat("\n");
            }
            return result;
        }
    }
}
