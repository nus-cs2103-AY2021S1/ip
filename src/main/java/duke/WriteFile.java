package duke;

import java.io.FileWriter;
import java.io.IOException;

/**
 * WriteFile class to write task data from duke to specified file location.
 */

public class WriteFile {
    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * The main method to run the WriteFile file
     * @param args
     */
    public static void main(String[] args) {
        String file2 = "/Users/ngjiaxin/Desktop/AY2021/CS2103T/Week 2/data";
        try {
            writeToFile(file2, "first line" + System.lineSeparator() + "second line");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
