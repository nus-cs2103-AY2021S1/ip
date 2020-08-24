import java.util.ArrayList;

public class TaskListHandler {
    protected ArrayList<Task> taskList;

    public TaskListHandler(ArrayList<Task> list) {
        this.taskList = list;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void addToList(Task t) {
        taskList.add(t);
    }

    public ArrayList<Task> clearList() {
        this.taskList = new ArrayList<>();
        System.out.println("The list of tasks has been cleared.");
        return taskList;
    }

    public void printList() throws DukeException {
        if (taskList.isEmpty()) {
            // Asks user for tasks when printing empty list
            throw new DukeException("\u2639 Oops, the list of tasks is empty, pls add tasks first.");
        }
        int listPos = 1;
        indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++,listPos++) {
            indent(2);
            System.out.println(listPos + ". " + taskList.get(i));
        }
        indent(1);
        System.out.println("You have " + taskList.size() + " task(s) in the list");
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }
}
