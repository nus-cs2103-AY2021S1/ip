package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Encapsulates the storage feature of the Duke program.
 */
public class Storage {
    private final Path path;

    /**
     * Returns a Storage instantiation with a specific file path.
     * @param filePath The path of the file to store the save file at
     */
    public Storage(String filePath) {
        path = Paths.get(filePath);
    }

    private TaskList makeDataFile() throws IOException {
        File file = path.toFile();
        TaskList list = new TaskList();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();

        return list;
    }

    /**
     * Attempts to load the save file.
     * @return a TaskList from the saved file, if one exists. If not, a new TaskList is returned.
     */
    public TaskList loadFile() {
        TaskList list = null;
        try {
            File file = path.toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (TaskList) ois.readObject();
            ois.close();
            //@@author
        } catch (IOException | ClassNotFoundException e1) {
            try {
                list = makeDataFile();
            } catch (FileNotFoundException e2) {
                try {
                    Files.createDirectories(path.getParent());
                    list = makeDataFile();
                } catch (IOException e) {
                    Ui.print(e.getMessage());
                }
            } catch (IOException e) {
                Ui.print("There was an error initialising your save file.\n" + e);
            }
        }

        if (list == null) {
            Ui.print("Running program without initializing a save.\nThis may cause errors when closing the program.");
            return new TaskList();
        } else {
            return list;
        }
    }

    /**
     * Attempts to save the TaskList to the respective path.
     * @param list the TaskList to be saved
     */
    public void saveFile(TaskList list) {
        try {
            File file = path.toFile();
            //@@author ktaekwon000-reused
            //Reused from https://stackoverflow.com/a/16111797 with minor modifications
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            oos.close();
            //@@author
        } catch (IOException e) {
            Ui.print("There was an error saving your data.\n" + e.getMessage());
        }
    }
}
