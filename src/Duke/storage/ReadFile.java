package Duke.storage;

import Duke.exception.DukeException;
import Duke.main.Parser;
import Duke.main.Formating;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * This class is to read the Duke.txt
 * at ./data.
 */
public class ReadFile {
    private String path;

    /**
     * Initialize a ReadFile object.
     *
     * @param path A string which contains
     *             the directory of the file
     *             that is to be read.
     */
    public ReadFile(String path) {
        this.path = path;
    }

    /**
     * This method is to convert the
     * tasks in Duke.txt into Parser.taskList.
     */
    public void readFile() {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fileReader);

            String currentLine = textReader.readLine();

            while (currentLine != null) {
                Formating<String> stringFormating = new Formating<>(currentLine);
                Parser.taskList.addMemory(stringFormating.stringToTask());
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.fileException();
        }
    }

    public void matchContent(String content) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fileReader);

            String currentLine = textReader.readLine();

            while (currentLine != null) {
                if (currentLine.contains(content)) {
                    Formating<String> stringFormating = new Formating<>(currentLine);
                    Parser.taskList.addMemory(stringFormating.stringToTask());
                }
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.FileException();
        }
    }
}
