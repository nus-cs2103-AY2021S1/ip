import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    /** Destination path of the duke.txt storage file. */
    private final String path;

    /**
     * Construct new ReadFile object.
     *
     * @param filePath Destination path of the duke.txt storage file.
     */
    public ReadFile(String filePath) {
        path = filePath;
    }

    /**
     * Returns the number of lines in the duke.txt storage file.
     *
     * @return Number of lines.
     * @throws IOException If input or output exceptions are encountered.
     */
    private int readLines() throws IOException {
        FileReader reader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String nextLine;
        int numLines = 0;

        while ((nextLine = bufferedReader.readLine()) != null) {
            ++numLines;
        }
        bufferedReader.close();
        return numLines;
    }

    /**
     * Populates the (saved) data from the duke.txt storage file into an array and returns it.
     *
     * @return An array of tasks stored in their toString() form.
     * @throws IOException If input or output exceptions are encountered.
     */
    public String[] openFile() throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int numLines = readLines();
        String[] data = new String[numLines];

        for (int i = 0; i < numLines; ++i) {
            data[i] = bufferedReader.readLine();
        }
        bufferedReader.close();
        return data;
    }
}
