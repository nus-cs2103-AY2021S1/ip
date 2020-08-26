package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A class that defines the file path where tasks will be read from and saved.
 */
public class Storage {

    File outputFile;
    File dir;

    /**
     * Constructs a Storage object.
     *
     * @param filepath  A String of the file path.
     */
    public Storage(String filepath) {
        this.outputFile = new File(filepath);
        this.dir = this.outputFile.getParentFile();
    }

    /**
     * Loads existing task list from file path, if it exists.
     *
     * @return  An Optional containing a stream of Strings of the lines in the file path, if it exists.
     * @throws FileNotFoundException  If erroneous file path is given.
     */
    public Optional<Stream<String>> load() throws FileNotFoundException {
        if (outputFile.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(outputFile));
            return Optional.of(reader.lines());
        }
        return Optional.empty();
    }

    /**
     * Saves the given TaskList onto the file path.
     *
     * @param taskList  the TaskList to be saved.
     * @throws IOException  If the file path is erroneous.
     */
    public void save(TaskList taskList) throws IOException {
        if (!this.dir.exists()) {
            dir.mkdirs();
        }
        outputFile.createNewFile();

        FileWriter writer = new FileWriter(outputFile);
        writer.write(taskList.fileText());
        writer.close();
    }
}
