package tasklist;

import constant.DukeConstants;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public List<String> getListInfo() {
        List<String> listInfo = new ArrayList<>();
        listInfo.add(DukeConstants.LIST_OUTPUT);

        int num = 0;
        for (Task output : this.list) {
            num++;
            listInfo.add(String.format("%d.%s", num, output));
        }
        return listInfo;
    }

    public String markDone(int idx) {
        Task task = this.list.get(idx);
        task.markDone();
        return task.toString();
    }

    public List<Task> getList() {
        return list;
    }

    public int getCurrentSize() {
        return this.list.size();
    }

    public String delete(int idx) {
        Task task = this.list.remove(idx);
        return task.toString();
    }
}
