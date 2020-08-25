package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    File file;

    Storage(String filePath)  {
        this.file = new File(filePath);
    }

    /**
     * Reads a file and update the List of tasks
     * @return a list of Tasks in recognisable string format
     * @throws DukeException if file is not found
     */
    public List<String> load() throws DukeException {
        try {
            Scanner s = new Scanner(file);
            List store = new ArrayList<>();
            while (s.hasNext()) {
                store.add(s.nextLine());
            }
            return store;

        } catch (FileNotFoundException e) {
            throw new DukeException("file not found");
        }
    }

    /**
     * Updates the current list in duke against the database
     * @param store the current list of tasks
     * @param filePath the file path of the database
     */

    public static void updateDatabase(List<Task> store, String filePath) {
        try {
            if (store.isEmpty()) {
                writeToFile(filePath, "");
            } else {
                for (int i = 0; i < store.size(); i++) {
                    if (i == 0) {
                        writeToFile(filePath, store.get(i).inputStyle());
                    } else {
                        appendToFile(filePath, System.lineSeparator() + store.get(i).inputStyle());
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(new DukeException("empty list"));
        }
    }

    /**
     * Writes a given text to file
     * @param filePath
     * @param textToAdd input text
     * @throws IOException if file not found
     */

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds on a given text to existing strings in file specified by the filePath
     * @param filePath
     * @param textToAppend the given text
     * @throws IOException if file not found
     */

    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}
