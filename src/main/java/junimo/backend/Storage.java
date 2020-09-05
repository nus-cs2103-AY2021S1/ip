package junimo.backend;

import junimo.task.Task;
import junimo.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.util.List;

/**
 * Deals with loadings tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Constructs an instance Storage object, that loads and saves tasks to file specified by filePath.
     * If file and/or parent directories does not exist yet, creates them.
     * @param filePath Specifies pathname of file to save tasks to and load tasks from.
     */
    public Storage(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            createFile(file);
        }
        this.file = file;
    }

    private void createFile(File file) {
        try {
            String dir = file.getParent();
            File dirFile = new File(dir);

            // Create file and parent directories if they do not exist yet
            dirFile.mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads file into a BufferedReader and returns the BufferedReader for use by TaskList to populate the task list.
     * @return BufferedReader reading from saved task file.
     * @throws FileNotFoundException If file of the Storage object is not found.
     */
    public BufferedReader load() throws FileNotFoundException {
        FileReader f = new FileReader(file);
        return new BufferedReader(f);
    }

    /**
     * Saves Tasks from taskList onto hard drive.
     * Overwrites saved txt file each time with updated values.
     * @param taskList TaskList containing list of tasks to save.
     */
    public void save(TaskList taskList) {
        try {
            // Delete old saved file if it exists
            boolean isDeleted = file.delete();
            if (isDeleted) {
                createFile(file);
            }

            // Write tasks to new file overwriting the old file
            FileWriter fw = new FileWriter(file);
            List<Task> tl = taskList.getTaskList();
            for (Task t : tl) {
                fw.write(t.getParsedTask());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }
}
