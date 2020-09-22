package duke.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Storage {
    /**
     * Saves task list onto a prescribed file.
     * @param tasks the tasklist to be saved
     * @throws IOException if file not successfully made (e.g. permission error)
     */
    public static void save(TaskList tasks) throws DukeException {
        // Save file
        Path dirPath = Paths.get("data");
        Path filePath = Paths.get("data", "duke.txt");

        // Possible error: directory doesn't exist
        if (!dirPath.toFile().exists()) {
            File data = new File(dirPath.toString());
            data.mkdir();
        }

        try {
            // Write to file
            String toWrite = tasks.toSave();
            BufferedWriter wr = new BufferedWriter(new FileWriter(filePath.toString()));
            wr.write(toWrite);
            wr.close();
        } catch (IOException e) {
            throw new DukeException("An IO error occurred.");
        }
    }

    /**
     * Loads a TaskList from a file.
     * @return a TaskList corresponding to the file input.
     * @throws FileNotFoundException if the file is not found.
     * @throws IOException if an I/O error occurs.
     */
    public static TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        Parser parser = new Parser();
        Path filePath = Paths.get("data", "duke.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath.toString()));
            String nextLine = reader.readLine();
            while (nextLine != null) {
                parser.parseCommand(nextLine, tasks).execute(tasks);
                nextLine = reader.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("An IO error occurred.");
        }
        return tasks;
    }

}
