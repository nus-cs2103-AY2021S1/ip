package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeIoException;

/**
 * The class that is responsible for reading and writing raw taskList data.
 */
public class Storage {

    private final File dataFile;

    /**
     * Initializes a Storage object.
     *
     * @param filePath The path of the data file.
     */
    public Storage(String filePath) {
        this.dataFile = new File(filePath);
    }

    /**
     * Retrives The raw data from the data file.
     *
     * @return An ArrayList of each line of raw data.
     * @throws DukeIoException If file was not found at the filePath.
     */
    public ArrayList<String> load() throws DukeIoException {
        ArrayList<String> res = new ArrayList<>();
        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                res.add(s);
            }
            return res;
        } catch (FileNotFoundException e) {
            throw new DukeIoException("PROJ_ROOT/" + dataFile.getPath() + " not found!");
        }

    }

    /**
     * Writes the raw data into the data file.
     *
     * @param data The raw data to be written.
     * @throws DukeIoException For exceptional cases where the program is unable to write to the data file.
     */
    public void save(ArrayList<String> data) throws DukeIoException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); i++) {
            sb.append(data.get(i));
            if (i != data.size() - 1) {
                sb.append('\n');
            }
        }

        if (dataFile.getParent() != null) {
            String parentDir = dataFile.getParent();
            File dataDir = new File(parentDir);
            if (!dataDir.exists()) {
                dataDir.mkdir(); // if intellij is slow in displaying data dir, right click -> reload from disk
            }
        }

        String textToPrint = sb.toString();
        try {
            FileWriter fw = new FileWriter(dataFile);
            fw.write(textToPrint);
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }

    }
}
