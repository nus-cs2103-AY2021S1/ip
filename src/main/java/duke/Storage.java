package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

/**
 * Encapsulates behaviour for all storage related actions.
 */
public class Storage {

    private final Path filePath;

    /**
     * Creates a new instance of the storage class pointed towards the desired filepath.
     * @param filePath File path that storage actions should be performed on.
     */
    public Storage(Path filePath) {
        assert filePath != null : "Storage filepath cannot be null";
        this.filePath = filePath;
    }

    /**
     * Creates a new instance of the Storage class that is pointed at the desired
     * filepath. The required folders and files are created if they do not exist.
     *
     * @throws DukeException If there are issues with creating, reading or writing to storage.
     */
    public void loadFromStorage() throws DukeException {
        try {
            // create data directory if it does not exist
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // create file if it does not exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("File System Error");
        }
    }

    /**
     * Appends a new line to the text file that the instance of the Storage class
     * is pointing to.
     *
     * @param line Line to be added to the text file.
     * @throws DukeException If there are issues with writing to storage.
     */
    public void writeLineToStorage(String line) throws DukeException {
        assert line != null : "Line to add to storage cannot be null";
        try {
            FileWriter fileWriter = new FileWriter(filePath.toString(), true);
            fileWriter.write(line + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Task could not be saved.");
        }
    }

    /**
     * Deletes a line from form the text file that the instance of the Storage
     * class is pointing to.
     *
     * @param lineToDelete Line to be deleted from the text file.
     * @throws DukeException If there are issues with deleting the line from storage.
     */
    public void deleteLineFromStorage(String lineToDelete) throws DukeException {
        assert lineToDelete != null : "Line to delete from storage cannot be null";
        try {
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
        } catch (IOException e) {
            throw new DukeException("Task could not be deleted");
        }
    }

    /**
     * Replaces a line in the text file that the instance of the Storage class is
     * pointing to with another line.
     *
     * @param oldLine Line to be replaced.
     * @param newLine Line that will be used to replace.
     * @throws DukeException If there are issues with writing to storage.
     */
    public void editLineInStorage(String oldLine, String newLine) throws DukeException {

        assert oldLine != null : "Line to be replaced cannot be null";
        assert newLine != null : "Line that will be used to replace cannot be null";

        try {
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
        } catch (IOException e) {
            throw new DukeException("Task could not be modified");
        }
    }
}
