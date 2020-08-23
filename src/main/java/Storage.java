import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public List<Task> getItems() throws IOException {
        List<Task> items = new ArrayList<>();
        ensureExistence();
        Scanner scanner = new Scanner(saveFile);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.strip().equals("")) continue;
            try {
                Task t = Parser.parse(line.substring(line.indexOf(' ') + 1));
                t.setDone(Boolean.parseBoolean(line.substring(0, line.indexOf(' '))));
                items.add(t);
            } catch (ChatterboxException | IndexOutOfBoundsException e) {
                System.err.println("One or more lines in the save file may have been corrupted.");
            }
        }
        return items;
    }

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
