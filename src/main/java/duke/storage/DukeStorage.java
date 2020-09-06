package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

// Class that handles the loading and saving of the save file.
public class DukeStorage implements Storage {
    private static final String ERROR_CREATE_SAVE = "An error has occurred when trying to create the save file!";
    private static final String ERROR_UPDATE_SAVE = "An error has occurred when updating the save file.";
    private static final String ERROR_READ_SAVE = "An error has occurred when reading the save file.";

    private final Path filePath;
    private ArrayList<String> saveLines;
    private boolean isActive = true;

    /**
     * Initialises the DukeStorage object.
     * Creates the save file in the specified path if it does not exists
     * then loads the save file.
     *
     * @param filePath Path path to save file.
     * @throws IOException If an IO error occurs while creating or loading the save file.
     */
    public DukeStorage(Path filePath) throws IOException {
        this.filePath = filePath;
        try {
            createSaveFile();
            loadData();
        } catch (IOException e) {
            throw new IOException(ERROR_CREATE_SAVE);
        }
    }

    private void createSaveFile() throws IOException {
        int len = filePath.getNameCount();
        Path directoriesToCreate = filePath.subpath(0, len - 1);
        Files.createDirectories(directoriesToCreate);
        boolean saveFileExists = java.nio.file.Files.exists(filePath);
        if (!saveFileExists) {
            new File(filePath.toString()).createNewFile();
        }
    }

    private void updateSaveFile() {
        if (!isActive) {
            return;
        }

        try {
            FileWriter myWriter = new FileWriter(filePath.toString());
            for (String line: saveLines) {
                myWriter.write(line + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(ERROR_UPDATE_SAVE);
        }
    }

    /**
     * Adds the input save string into the storage.
     *
     * @param saveString String line to be saved.
     */
    public void addLine(String saveString) {
        saveLines.add(saveString);
        updateSaveFile();
    }

    /**
     * Updates a specific line in the storage with the input string.
     *
     * @param index int index of line to update.
     * @param saveString String the string to update the specific line with.
     */
    public void updateLine(int index, String saveString) {
        saveLines.set(index, saveString);
        updateSaveFile();
    }

    /**
     * Removes a specific line in the storage.
     *
     * @param index int index of line to delete.
     */
    public void removeLine(int index) {
        saveLines.remove(index);
        updateSaveFile();
    }

    /**
     * Returns all the lines that are in the storage.
     *
     * @return ArrayList&lt;String> all the lines that are in the storage.
     */
    public ArrayList<String> getSavedLines() {
        return saveLines;
    }

    private void loadData() {
        // Prevent saving while loading
        isActive = false;
        String[] result = new String[0];
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath.toString()));
            result = in.lines().toArray(String[]::new);
            in.close();
        } catch (IOException e) {
            System.out.println(ERROR_READ_SAVE);
        } finally {
            isActive = true;
            this.saveLines = new ArrayList<>(Arrays.asList(result));
        }
    }
}
