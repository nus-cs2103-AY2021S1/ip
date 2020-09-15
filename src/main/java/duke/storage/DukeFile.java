package duke.storage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;

/**
 * Makes general approaches to access a file given a directory.
 */
public class DukeFile {
    protected Path path;

    /**
     * Constructs a DukeFile object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public DukeFile(String path) {
        this.path = Paths.get(path);
    }

    /**
     * Checks if the file in the path exists.
     *
     * @return True if the file described in the path exists.
     */
    protected boolean doesFileExist() {
        return Files.exists(path);
    }

    /**
     * Creates a new file in the path.
     */
    protected void createFile() {
        try {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println(DukeException.fileAlreadyExistException());
        }
    }

    /**
     * Reads the content of the file in the path.
     *
     * @return A list of Strings that represent the tasks.
     */
    protected List<String> readFile() {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(DukeException.fileException());
            return null;
        }
    }

    /**
     * Writes the taskStrings List into the file
     * with the class field path.
     *
     * @param taskStrings A List of String with task information.
     */
    protected void write(List<String> taskStrings) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(path);
            for (String taskString : taskStrings) {
                writer.write(taskString);
                writer.newLine();
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println(DukeException.fileWritingException());
        }
    }
}
