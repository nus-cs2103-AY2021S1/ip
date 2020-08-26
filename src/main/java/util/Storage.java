package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The storage class handles the logic of writing and reading files.
 */
public class Storage {
    /** File object containing the text file data. */
    private final File file;

    /**
     * Creates a new Storage instance.
     * 
     * @param filePath Name of file to be located or created.
     * @param directory Directory of file to be located or created.  
     * @throws IOException If errors are encountered in reading or writing to file.                  
     */
    public Storage(String filePath, String directory) throws IOException {
        File dir = new File(directory);
        file = new File(filePath);
        
        // make the directory if doesn't exist
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        
        // create the file if it doesn't exist
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Returns a list of tasks in their String format after reading the text data file.
     * 
     * @return List of Strings representing tasks
     * @throws FileNotFoundException If file to load data from is not found.              
     */
    public List<String> loadData() throws FileNotFoundException {
        List<String> lst = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            lst.add(line);
        }
        return lst;
    }

    /**
     * Adds a line to the file.
     * 
     * @param line Line to be added to the file.
     * @throws IOException If there is error writing to the file.
     */
    public void addLine(String line) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(line + "\n");
        fw.close();
    }

    /**
     * Deletes a line from the file.
     *
     * @param lineNum Line number to be deleted from the file.
     * @throws IOException If there is error writing to the file.
     */
    public void deleteLine(int lineNum) throws IOException {
        Path path = Path.of(file.getPath());
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        fileContent.remove(lineNum);
        Files.write(path, fileContent, StandardCharsets.UTF_8);
    }

    /**
     * Modifies a line from the file.
     *
     * @param lineNum Line number to be modified from the file.
     * @throws IOException If there is error writing to the file.
     */
    public void modifyLine(int lineNum) throws IOException {
        Path path = Path.of(file.getPath());
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
        String line = fileContent.get(lineNum);
        String updatedLine = line.replaceFirst("0", "1");
        fileContent.set(lineNum, updatedLine);
    }
}
