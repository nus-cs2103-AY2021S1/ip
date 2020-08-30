package chatterbox;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import chatterbox.task.Task;

/**
 * Handles the loading and saving of tasks.
 */
public class Storage {
    private static final String SAVE_FOLDER_PATH = "./data";
    private static final String SAVE_FILE_PATH = SAVE_FOLDER_PATH + "/chatterbox.txt";
    private final File saveFolder = new File(SAVE_FOLDER_PATH);
    private final File saveFile = new File(SAVE_FILE_PATH);

    /**
     * Creates the save folder and save file if they do not exist.
     *
     * @throws IOException  If folder or file cannot be created.
     */
    private void ensureExistence() throws IOException {
        if (!saveFolder.exists()) {
            saveFolder.mkdir();
            saveFile.createNewFile();
        } else if (!saveFile.exists()) {
            saveFile.createNewFile();
        }
    }

    /**
     * Gets task items from the saved file and parses them into a list of Task objects.
     *
     * @return  The list of tasks saved in the file.
     * @throws IOException  If the file cannot be read or parsed properly.
     */
    public List<Task> getItems() throws ChatterboxException, IOException {
        List<Task> items = new ArrayList<>();
        ensureExistence();
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
                throw new ChatterboxException("One or more lines in the save file may have been corrupted.");
            }
        }
        return items;
    }

    /**
     * Saves the list of Task objects into the save file in text format.
     *
     * @param items List of Task objects to save.
     * @throws IOException  If the program is unable to write to the save file.
     */
    public void saveItems(List<Task> items) throws IOException {
        StringBuilder saveText = new StringBuilder();
        for (Task t : items) {
            saveText.append(t.getDone() ? "true " : "false ");
            saveText.append(t.getInputString());
            saveText.append("\n");
        }
        ensureExistence();
        FileWriter writer = new FileWriter(SAVE_FILE_PATH);
        writer.write(saveText.toString());
        writer.close();
    }
}
