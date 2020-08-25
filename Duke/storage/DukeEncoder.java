package Duke.storage;

import java.util.ArrayList;
import java.util.List;

import Duke.data.Duke;
import Duke.data.task.Task;

public class DukeEncoder {
    /**
     * Encodes all the {@code Task} in the {@code toSave} into a list of decodable
     * and readable string presentation for storage.
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
