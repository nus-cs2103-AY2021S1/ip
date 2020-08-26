package duke.util;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class will handle the retrieving and storing into data file.
 */
public class Storage {
    /** The filepath to store or retrieve from. */
    private File file;

    /**
     * Constructs a storage object with the given file path.
     *
     * @param filePath The filepath to interact with.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates the file if it does not exist in the system.
     *
     * @throws DukeException if the creation of new file is not successful.
     */
    private void createFileIfNotExist() {
        if (file.isDirectory()) {
            file.mkdirs();
        }
        else {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException io) {
                throw new DukeException(io.getMessage());
            }
        }
    }

    /**
     * Reads the content of the file provided and store it in a list.
     *
     * @return The content of the file as a list of string.
     */
    public List<String> load() {
        createFileIfNotExist();
        return readAllLines();
    }

    private List<String> readAllLines() {
        List<String> content = new ArrayList<>();

        try {
            Scanner readFile = new Scanner(file);
            while(readFile.hasNextLine()) {
                String ln = readFile.nextLine();
                content.add(ln);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to find file");
        }

        return content;
    }

    /**
     * Saves the list of Strings into the file.
     *
     * @param contents A list of Strings to store into file
     * @return The status of which the saving to file is successful.
     */
    public boolean saveToFile(List<String> contents) {
        createFileIfNotExist();
        try {
            FileWriter writer = new FileWriter(this.file);
            for (String s : contents) {
                writer.write(s + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
