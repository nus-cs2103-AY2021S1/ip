package Duke.storage;

import Duke.exception.DukeException;
import Duke.main.Parser;
import Duke.main.Formating;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile {
    private String path;

    public ReadFile(String path) {
        this.path = path;
    }

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
