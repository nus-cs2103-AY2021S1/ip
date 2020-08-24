package luoyi.duke.storage;

import luoyi.duke.data.task.ITask;

import java.util.ArrayList;
import java.util.List;

/**
 * Encodes the tasks in a Duke object to a string for storage.
 */
public class TaskEncoder {
    /**
     * Returns a list of encoded task strings of a Duke object.
     *
     * @param tasks object whose tasks will be encoded.
     * @return A list of strings representing the tasks.
     */
    public static List<String> encodeTaskList(List<? extends ITask> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        tasks.forEach(x -> encodedTasks.add(x.getDataString()));
        return encodedTasks;
    }

}
