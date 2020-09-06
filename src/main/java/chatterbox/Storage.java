package chatterbox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chatterbox.task.Task;

/**
 * Handles the loading and saving of tasks.
 */
public class Storage {
    private static final String ERROR_FILE_CORRUPT = "One or more lines in the save file may have been corrupted.";

    private static final String SAVE_FOLDER_PATH = "./data";
    private static final String SAVE_FILE_PATH = SAVE_FOLDER_PATH + "/chatterbox.txt";
    private static final String ARCHIVE_FILE_PATH = SAVE_FOLDER_PATH + "/chatterbox_%d.bak";
    private final File saveFolder = new File(SAVE_FOLDER_PATH);
    private final File saveFile = new File(SAVE_FILE_PATH);

    /**
     * Creates the save folder and save file if they do not exist.
     *
     * @throws IOException  If folder or file cannot be created.
     */
    private void ensureSaveFileExistence() throws IOException {
        if (!saveFolder.exists()) {
            saveFolder.mkdir();
            saveFile.createNewFile();
        } else if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    private File getUnusedArchiveFile() {
        if (!saveFolder.exists()) {
            saveFolder.mkdir();
        }
        for (int i = 0; i < 100; i++) {
            String archiveFilename = String.format(ARCHIVE_FILE_PATH, i);
            File archiveFile = new File(archiveFilename);
            if (!archiveFile.exists()) {
                return archiveFile;
            }
        }
        // Returns the 0th file if all 0-99th archives are taken up
        return new File(String.format(ARCHIVE_FILE_PATH, 0));
    }

    /**
     * Gets task items from the saved file and parses them into a list of Task objects.
     *
     * @return  The list of tasks saved in the file.
     * @throws IOException  If the file cannot be read or parsed properly.
     */
    public List<Task> getItems() throws ChatterboxException, IOException {
        List<Task> items = new ArrayList<>();
        ensureSaveFileExistence();
        Scanner scanner = new Scanner(saveFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.strip().equals("")) {
                continue;
            }
            try {
                Task t = Parser.parseTask(line.substring(line.indexOf(' ') + 1));
                t.setDone(Boolean.parseBoolean(line.substring(0, line.indexOf(' '))));
                items.add(t);
            } catch (IndexOutOfBoundsException e) {
                throw new ChatterboxException(ERROR_FILE_CORRUPT);
            }
        }
        return items;
    }

    private String getSaveString(List<Task> items) {
        StringBuilder saveText = new StringBuilder();
        for (Task t : items) {
            saveText.append(t.getDone() ? "true " : "false ");
            saveText.append(t.getInputString());
            saveText.append("\n");
        }
        return saveText.toString();
    }

    /**
     * Saves the list of Task objects into the save file in text format.
     *
     * @param items List of Task objects to save.
     * @throws IOException  If the program is unable to write to the save file.
     */
    public void saveItems(List<Task> items) throws IOException {
        ensureSaveFileExistence();
        FileWriter writer = new FileWriter(SAVE_FILE_PATH);
        String saveString = getSaveString(items);
        writer.write(saveString);
        writer.close();
    }

    /**
     * Archives the list of Task objects into an archive file in text format, and empties the current save file.
     *
     * @throws IOException If archiving fails.
     */
    public void archiveItems() throws IOException {
        ensureSaveFileExistence();
        File archiveFile = getUnusedArchiveFile();
        Files.move(saveFile.toPath(), archiveFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        ensureSaveFileExistence(); // Create an empty save file again
    }
}
