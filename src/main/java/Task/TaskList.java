package task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
    }

    public TaskList(List<Task> list) {
        this.taskList = list;
    }

    public void addToTaskList(Task task) {
        this.taskList.add(task);
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    public void deleteFromTaskList(int index) {
        Task task = getTask(index);
        this.taskList.remove(index);
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void showList() {
        System.out.print("    ____________________________________________________________\n");
        System.out.print("     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.taskList.size(); i++) {
            System.out.println("     " + i + "." + this.taskList.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
