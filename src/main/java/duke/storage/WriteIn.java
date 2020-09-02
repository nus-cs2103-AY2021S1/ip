package duke.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import duke.exception.DukeException;

/**
 * This class is to write the information
 * into the file provided in the Directory class.
 */
public class WriteIn {
    private String path;
    private boolean appendToFile;

    /**
     * Initializes a WriteIn object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     * @param appendToFile A boolean of if the user want to make
     *                       the WriteIn work immediately.
     */
    public WriteIn(String path, boolean appendToFile) {
        this.path = path;
        this.appendToFile = appendToFile;
    }

    /**
     * Writes the input into the file whose path is from the
     * Directory class.
     *
     * @param input The input from the users.
     */
    public void writeToFile (String input) {
        try {
            FileWriter writeIn = new FileWriter(path, appendToFile);
            PrintWriter printWrite = new PrintWriter(writeIn);

            printWrite.println(input);

            printWrite.close();

        } catch (IOException e) {
            System.out.println(DukeException.fileException());
        }
    }
}
