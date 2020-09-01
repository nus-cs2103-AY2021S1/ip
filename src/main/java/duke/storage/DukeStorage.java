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
            createIfNotExist();
            loadSaveFile();
        } catch (IOException e) {
            throw new IOException("An error has occurred when trying to create the save file!");
        }
    }

    private void createIfNotExist() throws IOException {
        int len = filePath.getNameCount();
        Path directoriesToCreate = filePath.subpath(0, len - 1);
        Files.createDirectories(directoriesToCreate);
        if (!java.nio.file.Files.exists(filePath)) {
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
                try {
                    myWriter.write(line + "\n");
                } catch (IOException e) {
                    System.out.println("An error has occurred when updating the save file.");
                }
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred when updating the save file.");
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

    private String[] loadSaveFile() {
        // Prevent saving while loading
        isActive = false;
        String[] result = new String[0];
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath.toString()));
            result = in.lines().toArray(String[]::new);
            in.close();
        } catch (IOException e) {
            System.out.println(System.getProperty("user.dir"));
            System.out.println(filePath.toString());
            System.out.println(e.getMessage());
            System.out.println("An error has occurred when reading the save file.");
        } finally {
            isActive = true;
            this.saveLines = new ArrayList<>(Arrays.asList(result));
        }

        return result;
    }
}
