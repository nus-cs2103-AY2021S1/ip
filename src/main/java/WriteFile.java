import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteFile {
    /** Destination path of the duke.txt storage file. */
    private final String path;

    /** True if new data should be appended to current data. */
    private boolean isToAppend = false;

    /**
     * Constructs new WriteFile object.
     *
     * @param filePath Destination path of the duke.txt storage file.
     */
    public WriteFile(String filePath) {
        path = filePath;
    }

    /**
     * Constructs new WriteFile object (overloaded).
     *
     * @param filePath Destination path of the duke.txt storage file.
     * @param isToAppendValue Indication if new data should be appended to current data.
     */
    public WriteFile(String filePath, boolean isToAppendValue) {
        path = filePath;
        isToAppend = isToAppendValue;
    }

    /**
     * Writes data into the duke.txt storage file.
     *
     * @param text Data to be written.
     * @throws IOException If input or output errors are encountered.
     */
    public void writeToFile(String text) throws IOException {
        FileWriter write = new FileWriter(path, isToAppend);
        PrintWriter print = new PrintWriter(write);

        print.printf(text.equals("") ? "%s" : "%s" + "%n", text);
        print.close();
    }
}
