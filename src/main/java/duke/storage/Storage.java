package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Storage class handles all the operations when reading or writing files from the
 * hard disk.
 */
public class Storage {

    private static final String PIPE_SEPARATOR = "|";
    private final Path path;

    /**
     * Takes in a given filePath and saves it for reference.
     *
     * @param filePath file path from user input.
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Concatenates each line of the specified file if file is valid and returns
     * it as a String value. Otherwise, a DukeException will be thrown if there are
     * errors loading the file.
     *
     * @return String representation of the specified file.
     * @throws DukeException when file is not found.
     */
    public String load() throws DukeException {

        if (Files.exists((path))) {

            // create a File for the given file path
            File f = new File(String.valueOf(this.path));

            try {
                Scanner s = new Scanner(f);
                StringBuilder list = new StringBuilder();
                while (s.hasNext()) {
                    list.append(s.nextLine());
                    list.append(PIPE_SEPARATOR);
                }
                return list.toString();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        throw new DukeException("File does not exist");

    }

    /**
     * Returns the file path of this Storage object when it is created.
     *
     * @return Saved file path.
     */
    public Path getPath() {
        return this.path;
    }


    /**
     * Overrides a text file at a specified file path relative to the source.
     *
     * @param filePath  Specified file path to write to file.
     * @param textToAdd String value to write to file.
     */
    public void writeToFile(String filePath, String textToAdd) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Saves tasks in the TaskList Object into the hard disk so that it can be retrieved again.
     *
     * @param lst TaskList Object.
     */
    public void saveListToHardDisk(TaskList lst) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < lst.getSize(); i++) {
            Task targetTask = lst.get(i);

            list.append(targetTask.getStoreAs());
            list.append("\n");
        }

        writeToFile(this.path.toString(), list.toString());
    }
}
