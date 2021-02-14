package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Writes the data into the intended file path
 *
 * @throws IOException
 */

public class FileEditing {
    public static void writeToFile(String filePath, String data) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(data);
        fileWriter.close();
    }
}
