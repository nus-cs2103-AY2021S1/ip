import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskDecoder {
    public static List<ITask> decodeList(List<String> taskString) {
        List<ITask> taskList = new ArrayList<>();
        taskString.forEach(x -> taskList.add(decodeTask(x)));
        return taskList;
    }

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
