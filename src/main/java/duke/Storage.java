package duke;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import task.Task;
/**
 * The Storage class to encapsulate the storing & loading of items to the hard disk and to the Duke bot respectively
 *
 * @author  Ryan Lim
 */
public class Storage {
    /** file path of desired storage locationn */
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * retrieves the items that is stored on the hard disk previously in the form of a file. If the file does not exist
     * a new empty file is created
     *
     * @return File of the stored tasks or an empty file
     * @throws IOException if there is an error retrieving the file
     */
    public File load() throws IOException {
        File file = new File(this.filePath);
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
        }
        return file;
    }

    /**
     * saves the tasks that has been created/deleted/modified during the session
     *
     * @throws IOException if there is an error retrieving the file
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter updateFile = new FileWriter(this.filePath);
        ArrayList<Task> tasks = taskList.getTaskList();
        for (Task task : tasks) {
            updateFile.write(task.writeToFile() + "\n");
        }
        updateFile.close();
    }

    /**
     * saves the tasks that has been created/deleted/modified during the session
     *
     * @throws IOException if there is an error retrieving the file
     */
    public void save(Parser parser) throws IOException {
        HashMap<String, String> aliasCommandMap = parser.getAliasToCommandMap();
        FileWriter updateFile = new FileWriter(this.filePath);
        for (String alias : aliasCommandMap.keySet()) {
            updateFile.write(alias + "|" + aliasCommandMap.get(alias) + "\n");
        }
        updateFile.close();
    }

}
