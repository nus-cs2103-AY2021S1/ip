package duke;

import duke.task.Task;

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
        Ui.drawTopBorder();
        Ui.indent(1);
        System.out.println("I have cleared the list of tasks for you!");
        Ui.drawBottomBorder();
        return taskList;
    }

    public void printList() throws DukeException {
        if (taskList.isEmpty()) {
            // Asks user for tasks when printing empty list
            throw new DukeException("\u2639 Oops, the list of tasks is empty, pls add tasks first!");
        }
        int listPos = 1;
        Ui.drawTopBorder();
        Ui.indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++,listPos++) {
            Ui.indent(2);
            System.out.println(listPos + ". " + taskList.get(i));
        }
        Ui.indent(1);
        System.out.println("You have " + taskList.size() + " task(s) in the list");
        Ui.drawBottomBorder();
    }

}
