package duke.storage;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeArgumentException;
import duke.exception.DukeIoException;
import duke.parser.FileParser;
import duke.task.Task;


/**
 * Class that encapsulates the saving and reading of tasks.
 */
public class Storage {

    private final File file;
    private final TaskList tasks;

    /**
     * Constructor for a Storage object.
     *
     * @param path The path to read/write the file to
     * @throws DukeIoException if it is unable to create the file or if an IO error occurs
     */
    public Storage(String path) throws DukeIoException {
        try {
            tasks = new TaskList();
            file = new File(path);
            file.createNewFile(); // makes new file only if not already existing
            assert(file.exists()) : "File does not exist";
            BufferedReader csvReader = new BufferedReader(new FileReader(file));
            String line;
            // Add all the parsed lines to the ArrayList
            while ((line = csvReader.readLine()) != null) {
                tasks.add(FileParser.parseLine(line));
            }
        } catch (IOException ie) {
            throw new DukeIoException("Could not create Storage object due to IO error.");
        }
    }

    /**
     * Adds the given Task to the list of tasks and appends the new task to the data file.
     *
     * @param task the Task to be added
     * @throws IOException if an IOException occurs during writing
     */
    public void add(Task task) throws IOException {
        // TODO: Catch IOException into DukeIoException
        tasks.add(task);
        String taskString = FileParser.convertTaskToSaveFormat(task);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        bw.append(taskString);
        bw.flush();
        bw.close();
    }

    /**
     * Removes the task at the given index from the TaskList,
     * then clears the existing file and rewrites the contents of the list back to it.
     *
     * @param index The index of the task to be removed
     * @return The deleted task
     * @throws DukeIoException                if an IOException occurs during writing.
     * @throws ArrayIndexOutOfBoundsException if the given index is out of range.
     */
    public Task delete(int index) throws DukeIoException, ArrayIndexOutOfBoundsException {
        Task deleted = tasks.remove(index);
        rewrite();
        return deleted;
    }

    /**
     * Marks the task at the given index of the TaskList as completed,
     * then clears the existing file and rewrites the contents of the list back to it.
     *
     * @param index the index of the Task to be marked as completed.
     * @return the Task that was marked as completed
     * @throws DukeIoException       if an error occurs during write to file.
     * @throws DukeArgumentException if the index given is out of bounds
     */
    public Task complete(int index) throws DukeIoException, DukeArgumentException {
        Task completed = tasks.complete(index);
        rewrite();
        return completed;
    }

    /**
     * Returns the list of Tasks whose name contains the given keyword.
     *
     * @param keyword the keyword in lower-case to be searched for
     * @return TaskList containing only matching tasks
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();
        for (Task t : tasks.list()) {
            if (t.getName().toLowerCase().contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }

    /**
     * Returns the index of the Task within the TaskList
     * @param t The Task to search for
     * @return the index of the Task
     */
    public int indexOf(Task t) {
        return tasks.indexOf(t);
    }

    /**
     * Clears the existing file and rewrites the contents of the list to the file.
     *
     * @throws DukeIoException if there are issues with the IO operations
     */
    private void rewrite() throws DukeIoException {
        try {
            new FileWriter(file, false).close();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task t : tasks.list()) {
                bw.append(FileParser.convertTaskToSaveFormat(t));
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeIoException("IO Exception when writing to file. ");
        }
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
