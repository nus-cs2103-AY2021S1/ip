package duke;

import duke.exception.DukeIOException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class that is responsible for reading
 * and writing raw taskList data.
 */
public class Storage {
    private final File f;

    /**
     * Initializes a Storage object.
     * @param filePath The path of the data file.
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * Retrives The raw data from the data file.
     * @return An ArrayList of each line of raw data.
     * @throws DukeIOException If file was not found at the filePath.
     */
    public ArrayList<String> load() throws DukeIOException {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                res.add(s);
            }
            return res;
        } catch (FileNotFoundException e) {
            throw new DukeIOException("PROJ_ROOT/" + f.getPath() + " not found!");
        }

    }

    /**
     * Writes the raw data into the data file.
     * @param data The raw data to be written.
     * @throws DukeIOException For exceptional cases where
     * the program is unable to write to the data file.
     */
    public void save(ArrayList<String> data) throws DukeIOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i != data.size() - 1) {
                sb.append('\n');
            }
        }

        if (f.getParent() != null) {
            String parentDir = f.getParent();
            File dataDir = new File(parentDir);
            if (!dataDir.exists()) {
                dataDir.mkdir(); // if intellij is slow in displaying data dir, right click -> reload from disk
            }
        }

        String textToPrint = sb.toString();
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(textToPrint);
            fw.close();
        } catch (IOException e) {
            throw new DukeIOException(e.getMessage());
        }

    }
}
