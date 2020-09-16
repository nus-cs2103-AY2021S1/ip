package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.exception.Exceptions;

/**
 * Writes the information into the file provided in the Directory class.
 */
public class DukeFileWriter extends DukeFile {
    private boolean appendToFile;

    /**
     * Constructs a Duke file writer.
     *
     * @param path a string which contains
     *             the directory of the file
     *             that is to be read.
     * @param appendToFile a boolean of if the user want to make
     *                       the DukeFileWriter work immediately.
     */
    public DukeFileWriter(String path, boolean appendToFile) {
        super(path);
        this.appendToFile = appendToFile;
    }

    /**
     * Writes the input into the file whose path is from the
     * Directory class.
     *
     * @param input the input from the users.
     */
    public void writeToFile (String input) throws DukeException {
        try {
            if (appendToFile) {
                Files.write(path, (input + "\n").getBytes(),
                        StandardOpenOption.APPEND);
            } else {
                List<String> inputList = new ArrayList<>();
                inputList.add(input);
                write(inputList);
            }
        } catch (IOException | DukeException ex) {
            throw new DukeException(Exceptions.WRITINGEXCEPTION);
        }
    }
}
