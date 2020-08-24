package storage;

import data.TaskList;

import java.util.ArrayList;
import java.util.List;

// Encodes the data.TaskList object into a data file for storage.
public class TaskListEncoder {

    public static List<String> encodeTaskList(TaskList toSave) {
        final List<String> encodedTasks = new ArrayList<>();
        toSave.getList().forEach(task -> encodedTasks.add(task.fileFormat()));
        return encodedTasks;
    }

}
