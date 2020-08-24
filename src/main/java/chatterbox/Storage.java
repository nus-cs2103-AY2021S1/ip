package chatterbox;

import chatterbox.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the loading and saving of tasks.
 */
public class Storage {
    private static final String SAVE_FOLDER_PATH = "./data";
    private static final String SAVE_FILE_PATH = SAVE_FOLDER_PATH + "/chatterbox.txt";
    private final File saveFolder = new File(SAVE_FOLDER_PATH);
    private final File saveFile = new File(SAVE_FILE_PATH);

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
    public List<Task> getItems() throws IOException {
        List<Task> items = new ArrayList<>();
        ensureExistence();
        Scanner scanner = new Scanner(saveFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.strip().equals("")) continue;
            try {
                Task t = Parser.parseTask(line.substring(line.indexOf(' ') + 1));
                t.setDone(Boolean.parseBoolean(line.substring(0, line.indexOf(' '))));
                items.add(t);
            } catch (ChatterboxException | IndexOutOfBoundsException e) {
                Ui.showErrorMessage("One or more lines in the save file may have been corrupted.");
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
