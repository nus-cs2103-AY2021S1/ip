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
public class Storage {
    private final Path FILE_PATH;
    private ArrayList<String> SAVE_LINES;
    private boolean isActive = true;

    public Storage(Path filePath) throws IOException {
        this.FILE_PATH = filePath;
        loadSaveFile();
        try {
            createIfNotExist();
        } catch (IOException e) {
            throw new IOException("An error has occurred when trying to create the save file!");
        }
    }

    private void createIfNotExist() throws IOException {
        int len = FILE_PATH.getNameCount();
        Path directoriesToCreate = FILE_PATH.subpath(0, len - 1);
        Files.createDirectories(directoriesToCreate);
        if (!java.nio.file.Files.exists(FILE_PATH)) {
            new File(FILE_PATH.toString()).createNewFile();
        }
    }

    private void updateSaveFile() {
        if (!isActive) {
            return;
        }

        try {
            FileWriter myWriter = new FileWriter(FILE_PATH.toString());
            for (String line: SAVE_LINES) {
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

    public void addLine(String saveString) {
        SAVE_LINES.add(saveString);
        updateSaveFile();
    }

    public void updateLine(int index, String saveString) {
        SAVE_LINES.set(index, saveString);
        updateSaveFile();
    }

    public void removeLine(int index) {
        SAVE_LINES.remove(index);
        updateSaveFile();
    }

    public ArrayList<String> getSavedLines() {
        return SAVE_LINES;
    }

    private String[] loadSaveFile() {
        // Prevent saving while loading
        isActive = false;
        String[] result = new String[0];
        try {
            BufferedReader in = new BufferedReader(new FileReader(FILE_PATH.toString()));
            result = in.lines().toArray(String[]::new);
            in.close();
        } catch(IOException e) {
            System.out.println("An error has occurred when reading the save file.");
        } finally {
            isActive = true;
            this.SAVE_LINES = new ArrayList<>(Arrays.asList(result));
        }

        return result;
    }
}
