package src.main.java.duke.storage;

import java.util.ArrayList;
import java.util.List;

import src.main.java.duke.data.Duke;
import src.main.java.duke.data.task.Task;

/**
 * Represents an encoder that encodes all the {@code Task} in the {@code toSave} into a list of decodable.
 */
public class DukeEncoder {
    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable
     * and readable string presentation for storage.
     *
     * @param toSave the duke object that is required to save
     *
     * @return a list of strings that is encoded
     */
    public static List<String> encodeDuke(Duke toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.getTaskList().forEach(task -> encodedTasks.add(encodeTaskToString(task)));
        return encodedTasks;
    }

    /**
     * Encodes the {@code task} into a decodable and readable string representation.
     */
    private static String encodeTaskToString(Task task) {
        return task.toWriteString();
    }
}
