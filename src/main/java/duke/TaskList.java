package duke;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList extends ArrayList<Task> {
    List<Task> tList;

    public TaskList() {
        tList = new ArrayList<Task>();
    }

    public TaskList(List<Task> t) {
        tList = t;
    }

    public List<Task> filter(String keyword) {
        return tList.stream().filter(task -> task.getName().contains(keyword)).collect(Collectors.toList()); // Cast correctly?
    }

}
