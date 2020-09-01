package domain;

import constant.DukeConstants;
import storage.Storage;
import tasklist.TaskList;
import task.Task;

public class DukeDomain {

    private final Storage storage = new Storage();
    private final TaskList taskList = new TaskList(storage.load());

    public String markTaskDone(int idx) {
        Task task = this.taskList.markDone(idx);
        String response = String.format("%s\n%s%s[%s] %s", DukeConstants.DONE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT,
                task.getStatusIcon(), task.getTitle());
        return response;
    }

    public String deleteTask(int idx) {
        Task task = this.taskList.delete(idx);
        String response = String.format("%s\n%s%s%s", DukeConstants.DELETE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT, task);
        return response;
    }

    public int getCurrentTaskListSize() {
        return this.taskList.getSize();
    }

    public void outputTask(Task task) {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.ADD_TASK_OUTPUT);
        System.out.printf("%s%s%s\n", DukeConstants.IDENT, DukeConstants.IDENT, task);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, taskList.getSize());
        System.out.println(DukeConstants.LINE);
    }

    public void addToList(Task task) {
        taskList.add(task);
    }

    public void saveList() {
        this.storage.save(this.taskList.getList());
    }

    public void printList() {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.LIST_OUTPUT);
        this.taskList.print();
        System.out.println(DukeConstants.LINE);
    }
}
