import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the saving system for tasks.
 */
public class DukeSaver {

    /**
     * Holds the path to the saved data file.
     */
    private String savePath;

    /**
     * Constructor for the save system.
     *
     * @param savePath Path to the save file.
     */
    public DukeSaver(String savePath) {
        this.savePath = savePath;
        initSaveDir();
    }

    /**
     * Initializes the directory and save file.
     * If already exists, does not do anything.
     */
    public void initSaveDir() {
        try {
            File saveFile = new File(this.savePath);
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Serializes all the tasks in the list and
     * saves them in the save file.
     *
     * @param taskList The list of tasks.
     */
    public void saveData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(savePath);
            for (Task task : taskList.getList()) {
                fw.write(task.serialize() + "\n");
            }
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Parses all the lines in the save file into tasks.
     * Loads all the tasks into the task list.
     *
     * @param taskList The list of tasks (technically empty).
     */
    public void loadData(TaskList taskList) {
        try {
            File saveFile = new File(savePath);
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                taskList.addTask(Task.parse(line));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
