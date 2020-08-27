package duke;

import duke.task.Task;

import java.util.List;

public class Ui {

    TaskList taskList;

    public Ui(TaskList taskList) {
        this.taskList = taskList;
    }

    public void greeting() {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?\n\n";
        System.out.print(greeting);
    }

    public void help() {
        String msg = String.join("\n",
                "Adding task:",
                "todo <desc>",
                "deadline <desc> /by <desc>",
                "event <desc> /at <desc>",
                "",
                "list - show all added tasks",
                "done <taskId> - mark the task as done",
                "delete <taskId> - delete the task",
                "bye - close Duke");
        System.out.println(msg + "\n");
    }

    public void quit() {
        System.out.println("Adios!");
    }

    public void list() {
        List<Task> tasks = taskList.getTasks();
        if (tasks.size() == 0) {
            System.out.println("No tasks in the list wohoo!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                String task = tasks.get(i).toString();
                String message = (i + 1) + ". " + task;
                System.out.println(message);
            }
        }
        System.out.print("\n");
    }

    public void markAsDone(Task task) {
        System.out.println("Nice! I've marked it done - " + task.toString());
        System.out.print("\n");
    }

    private void summary() {
        System.out.println("Now you have " + taskList.taskSize() + " tasks in the list");
    }

    public void delete(Task task) {
        System.out.println("Noted! I've removed this task - " + task.toString());
        summary();
        System.out.print("\n");
    }

    public void add(Task task) {
        System.out.println("Added '" + task.toString() + "' to list of tasks");
        summary();
        System.out.print("\n");
    }

}
