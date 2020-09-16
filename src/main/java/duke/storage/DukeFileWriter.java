package duke.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;

/**
 * Writes the information into the file provided in the Directory class.
 */
public class DukeFileWriter extends DukeFile {
    private boolean appendToFile;

    /**
     * Constructs a DukeFileWriter object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     * @param appendToFile A boolean of if the user want to make
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
     * @param input The input from the users.
     */
    public void writeToFile (String input) {
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
            System.out.println(DukeException.fileWritingException());
        }
    }
}
