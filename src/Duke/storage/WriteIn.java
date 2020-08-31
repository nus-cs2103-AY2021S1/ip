package Duke.storage;

import Duke.exception.DukeException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * This class is to write the information
 * into the file provided in the Directory class.
 */
public class WriteIn {
    private String path;
    private boolean append_to_file;

    /**
     * Initializes a WriteIn object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     * @param append_to_file A boolean of if the user want to make
     *                       the WriteIn work immediately.
     */
    public WriteIn(String path, boolean append_to_file) {
        this.path = path;
        this.append_to_file = append_to_file;
    }

    /**
     * Writes the input into the file whose path is from the
     * Directory class.
     *
     * @param input The input from the users.
     */
    public void writeToFile (String input) {
        try {
            FileWriter writeIn = new FileWriter(path, append_to_file);
            PrintWriter printWrite = new PrintWriter(writeIn);

            printWrite.println(input);

            printWrite.close();

        } catch (IOException e) {
            DukeException.FileException();
        }
    }
}
