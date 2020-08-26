package Duke.storage;

import Duke.exception.DukeException;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriteIn {
    private String path;
    private boolean append_to_file;

    public WriteIn(String path, boolean append_to_file) {
        this.path = path;
        this.append_to_file = append_to_file;
    }

    public void writeToFile(String input) {
        try {
            FileWriter writeIn = new FileWriter(path, append_to_file);
            PrintWriter printWrite = new PrintWriter(writeIn);

            printWrite.println(input);

            printWrite.close();

        } catch (IOException e) {
            DukeException.fileException();
        }
    }
}
