package duke.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import duke.tasks.TaskList;

/**
 * Loads the saved TaskList.
 */
public class TextToTaskListConverter {

    /**
     * @param filePath Filepath where the file is stored.
     * @return TaskList that has been saved previously.
     */
    public static TaskList readFile(String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            TaskList taskList = (TaskList) objectInputStream.readObject();
            objectInputStream.close();
            return taskList;
        } catch (FileNotFoundException e) {
            return new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
