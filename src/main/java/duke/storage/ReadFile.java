package main.java.duke.storage;

import main.java.duke.exception.DukeException;
import main.java.duke.main.Format;
import main.java.duke.main.Parser;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

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
                Parser.taskList.addMemory(stringFormat.stringToTask());
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.FileException();
        }
    }

    public void matchContent(String content) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader textReader = new BufferedReader(fileReader);

            String currentLine = textReader.readLine();

            while (currentLine != null) {
                if (currentLine.contains(content)) {
                    Format<String> stringFormat = new Format<>(currentLine);
                    Parser.taskList.addMemory(stringFormat.stringToTask());
                }
                currentLine = textReader.readLine();
            }

            textReader.close();

        } catch (IOException e) {
            DukeException.FileException();
        }
    }
}
