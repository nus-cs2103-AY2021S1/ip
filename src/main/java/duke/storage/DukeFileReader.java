package duke.storage;

import java.util.List;

import duke.tools.Format;
import duke.tools.Parser;



/**
 * This class extends the DukeFile class
 * and is to read the file in the path directory.
 */
public class DukeFileReader extends DukeFile {

    /**
     * Constructs a DukeFileReader object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public DukeFileReader(String path) {
        super(path);
    }


    /**
     * Loads all the strings back to the Parser list as tasks.
     */
    public void loadFile() {
        if (!doesFileExist()) {
            createFile();
        }
        List<String> taskStrings = readFile();
        for (String taskString : taskStrings) {
            Format<String> stringFormat = new Format<>(taskString);
            Parser.getTaskList().addMemory(stringFormat.stringToTask());
        }
    }

    /**
     * Finds the Tasks whose detail contains
     * the content.
     *
     * @param content User input.
     */
    public void matchContent(String content) {
        if (doesFileExist()) {
            List<String> taskStrings = readFile();
            for (String taskString : taskStrings) {
                if (taskString.contains(content)) {
                    Format<String> stringFormat = new Format<>(taskString);
                    Parser.getTaskList().addMemory(stringFormat.stringToTask());
                }
            }
        }
    }
}
