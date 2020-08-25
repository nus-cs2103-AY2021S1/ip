package luoyi.duke.storage;

import luoyi.duke.data.task.Deadline;
import luoyi.duke.data.task.Event;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.ToDo;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskDecoder class to encapsulate Task decoding used for storage.
 * Format is [T/D/E]|[0/1]|[description]|[time].
 */
public class TaskDecoder {
    /**
     * Returns a list of task objects from a list of task strings.
     * Decodes a list of task strings into task objects.
     *
     * @param taskString List of task in string representation.
     * @return List of task objects.
     */
    public static List<ITask> decodeList(List<String> taskString) {
        List<ITask> taskList = new ArrayList<>();
        taskString.forEach(x -> taskList.add(decodeTask(x)));
        return taskList;
    }

    /**
     * Returns a single task by decoding.
     *
     * @param taskString Task in string representation.
     * @return Task object.
     */
    private static ITask decodeTask(String taskString) {
        String[] list = taskString.split("\\|");
        ITask task;
        switch (list[0]) {
        case "T":
            task = ToDo.getToDo(list[2]);
            break;
        case "D":
            task = Deadline.getDeadline(list[2], list[3]);
            break;
        case "E":
            task = Event.getEvent(list[2], list[3]);
            break;
        default:
            throw new IllegalArgumentException("Unable to decode!");
        }

        if (list[1].equals("1")) {
            task = task.markComplete();
        }

        return task;
    }
}
