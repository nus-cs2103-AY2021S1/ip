package duke.storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import duke.exception.DukeException;
import duke.main.Format;
import duke.main.Parser;



/**
 * This class is to read the file in
 * the Directory class.
 */
public class ReadFile {
    private String path;

    /**
     * Initializes a ReadFile object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public ReadFile(String path) {
        this.path = path;
    }

    /**
     * Converts the tasks in Directory file into Parser.taskList.
     */
    public void readFile() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fileReader);

            String currentLine = textReader.readLine();

            while (currentLine != null) {
                Format<String> stringFormat = new Format<>(currentLine);
                Parser.getTaskList().addMemory(stringFormat.stringToTask());
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.fileException();
        }
    }

    /**
     * Finds the Tasks whose detail contains
     * the content.
     *
     * @param content User input.
     */
    public void matchContent(String content) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fileReader);

            String currentLine = textReader.readLine();

            while (currentLine != null) {
                if (currentLine.contains(content)) {
                    Format<String> stringFormat = new Format<>(currentLine);
                    Parser.getTaskList().addMemory(stringFormat.stringToTask());
                }
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.fileException();
        }
    }
}
