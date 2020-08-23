package domains;

import constants.DukeConstants;
import tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class DukeDomain {

    private static final List<Task> TASK_LIST = new ArrayList<>();

    public void markTaskDone(int idx) {
        Task task = TASK_LIST.get(idx);
        task.markDone();
        String response = String.format("%s\n%s%s[%s] %s", DukeConstants.DONE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT,
                task.getStatusIcon(), task.getTitle());
        printResponse(response);
    }


    public void printResponseWithListSize(String response) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, TASK_LIST.size());
        System.out.println(DukeConstants.LINE);
    }

    public void deleteTask(int idx) {
        Task task = TASK_LIST.remove(idx);
        String response = String.format("%s\n%s%s%s", DukeConstants.DELETE_OUTPUT,
                DukeConstants.IDENT, DukeConstants.IDENT, task);
        printResponseWithListSize(response);
    }

    public void printResponse(String response) {
        System.out.println(DukeConstants.LINE);
        System.out.printf("%s%s\n", DukeConstants.IDENT, response);
        System.out.println(DukeConstants.LINE);
    }

    public void outputTask(Task task) {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.ADD_TASK_OUTPUT);
        System.out.printf("%s%s%s\n", DukeConstants.IDENT, DukeConstants.IDENT, task);
        System.out.printf("%sNow you have %d tasks in the list.%n",
                DukeConstants.IDENT, TASK_LIST.size());
        System.out.println(DukeConstants.LINE);
    }


    public void addToList(Task task) {
        TASK_LIST.add(task);
    }

    public void printList() {
        System.out.println(DukeConstants.LINE);
        System.out.println(DukeConstants.IDENT + DukeConstants.LIST_OUTPUT);
        int num = 0;
        for (Task output : TASK_LIST) {
            num++;
            System.out.printf("%s%d.%s\n", DukeConstants.IDENT, num, output);
        }
        System.out.println(DukeConstants.LINE);
    }
}
