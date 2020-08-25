package duke.util;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Creates the file if it does not exist.
     */
    private void createFileIfNotExist() {
        if (file.isDirectory())
            file.mkdirs();
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
     * Reads the content of the file provided and store it in a list
     * @param f File to be read from
     * @return the content of the file
     */
    private List<String> readAllLines(File f) {
        List<String> content = new ArrayList<>();

        try {
            Scanner readFile = new Scanner(f);
            while(readFile.hasNextLine()) {
                String ln = readFile.nextLine();
                content.add(ln);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to find file");
        }

        return content;
    }

    public List<String> load() {
        createFileIfNotExist();
        return readAllLines(file);
    }

    public boolean saveToFile(List<String> taskList) {
        createFileIfNotExist();
        try {
            FileWriter writer = new FileWriter(this.file);
            for (String s : taskList) {
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
