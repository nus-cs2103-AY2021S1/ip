package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

public class Storage {
    
    private final Path filePath;
    
    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a new instance of the Storage class that is pointed at the desired
     * filepath. The required folders and files are created if they do not exist.
     * 
     * @param filePath filepath that Storage object will read from and write to. 
     * @return new Storage object that stores changes to the specified filepath.
     * @throws IOException If there are issues with creating, reading or writing to storage.
     */
    public static Storage loadStorage(Path filePath) throws IOException {
        // create data directory if it does not exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // create file if it does not exist
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        
        return new Storage(filePath);
    }

    /**
     * Appends a new line to the text file that the instance of the Storage class
     * is pointing to.
     * 
     * @param line Line to be added to the text file.
     * @throws IOException If there are issues with writing to storage.
     */
    public void writeLineToStorage(String line) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath.toString(), true);
        fileWriter.write(line + System.lineSeparator());
        fileWriter.close();
    }

    /**
     * Deletes a line from form the text file that the instance of the Storage
     * class is pointing to.
     * 
     * @param lineToDelete Line to be deleted from the text file.
     * @throws IOException If there are issues with deleting the line from storage.
     */
    public void deleteLineFromStorage(String lineToDelete) throws IOException {
        FileReader reader = new FileReader(filePath.toString());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder fileData = new StringBuilder();

        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.equals(lineToDelete)) {
                fileData.append(line);
                fileData.append(System.lineSeparator());
            }
        }

        FileWriter fileWriter = new FileWriter(filePath.toString(), false);
        fileWriter.write(fileData.toString());
        fileWriter.close();
    }

    /**
     * Replaces a line in the text file that the instance of the Storage class is 
     * pointing to with another line.
     * 
     * @param oldLine Line to be replaced.
     * @param newLine Line that will be used to replace.
     * @throws IOException If there are issues with writing to storage.
     */
    public void editLineInStorage(String oldLine, String newLine) throws IOException {

        FileReader reader = new FileReader(filePath.toString());
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder oldFileData = new StringBuilder();
        
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            oldFileData.append(line);
            oldFileData.append(System.lineSeparator());
        }
        
        oldLine = Pattern.quote(oldLine);
        String newData = oldFileData.toString().replaceFirst(oldLine, newLine);
        
        FileWriter fileWriter = new FileWriter(filePath.toString(), false);
        fileWriter.write(newData);
        fileWriter.close();
    }
}
