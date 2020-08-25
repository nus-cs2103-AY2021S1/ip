package duke.util;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<String> input) {
        taskList = new ArrayList<>();
        for (String s: input) {
            taskList.add(Util.convertStringToTask(s));
        }
    }

    public List<String> export() {
        return taskList
                .stream()
                .map(x -> x.getSaveToFileString())
                .collect(Collectors.toList());
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public boolean add(Task t) {
        return taskList.add(t);
    }

    public Task remove(int idx) {
        return taskList.remove(idx);
    }

}
