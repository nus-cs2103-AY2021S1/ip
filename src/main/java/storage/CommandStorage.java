package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.DukeException;
import task.Task;

/**
 * A CommandStorage object deals with loading past user commands from a file and saving new ones to the same file.
 *
 * @author ameliatjy
 * @version 1.0
 * @since 2020-09-08
 */
public class CommandStorage extends Storage {

    private Task lastDeletedTask;

    /**
     * Public constructor of CommandStorage object.
     *
     * @param filePath Path where user commands can be saved to.
     * @param lastDeletedTask Task object representing the last Task that was deleted.
     */
    public CommandStorage(String filePath, Task lastDeletedTask) {
        super(filePath);
        this.lastDeletedTask = lastDeletedTask;
    }

    public void updateLastDeletedTask(Task lastDeletedTask) {
        this.lastDeletedTask = lastDeletedTask;
    }

    public Task getLastDeletedTask() {
        return this.lastDeletedTask;
    }

    /**
     * Stores new user command in a file.
     *
     * @param command New user command to be added to the file.
     * @throws IOException If directory or file does not exist.
     */
    public void writeToFile(String command) throws IOException {
        File file = new File(this.filePath);
        File directory = new File(file.getParent());
        if (!directory.exists()) {
            // directory does not exist
            directory.mkdir();
        }
        if (!file.exists()) {
            // file does not exist
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(command + "\n");
        fw.close();
    }

    /**
     * Obtain list of commands from .txt file.
     *
     * @param br BufferedReader object used to read the file.
     * @return ArrayList of commands in String.
     * @throws IOException If an I/O exception of some sort has occurred.
     */
    public ArrayList<String> getPastCommands(BufferedReader br) throws IOException {
        ArrayList<String> pastCommands = new ArrayList<>();
        String line = br.readLine();
        while (line != null) {
            pastCommands.add(line);
            line = br.readLine();
        }
        return pastCommands;
    }

    /**
     * Gets all past user commands stored locally.
     *
     * @return ArrayList of commands in String.
     * @throws DukeException If file does not exists.
     * @throws IOException By FileReader object.
     */
    public ArrayList<String> load() throws DukeException, IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            throw new DukeException("There are no past commands to undo.");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        return getPastCommands(br);
    }

    /**
     * Clears the file on ending a session.
     *
     * @throws IOException By FileWriter object.
     */
    public void reset() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }
}
