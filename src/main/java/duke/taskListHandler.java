package duke;

import duke.task.Task;

import java.util.ArrayList;

public class taskListHandler {
    protected ArrayList<Task> tasks;

    public taskListHandler(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addToList(Task t) {
        tasks.add(t);
    }

    public ArrayList<Task> clearList() {
        tasks = new ArrayList<>();
        System.out.println("The list of tasks has been cleared.");
        return tasks;
    }

    public void printList() throws DukeException {
        if (tasks.isEmpty()) {
            // Asks user for tasks when printing empty list
            throw new DukeException("\u2639 Oops, the list of tasks is empty, pls add tasks first");
        }
        int listPos = 1;
        indent(1);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++, listPos++) {
            indent(2);
            System.out.println(listPos + ". " + tasks.get(i));
        }
        indent(1);
        System.out.println("You have " + tasks.size() + " task(s) in the list");
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }
}
