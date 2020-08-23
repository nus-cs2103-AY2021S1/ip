package duke;

import duke.task.Task;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void start() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printAddTask(TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + taskList.getTask(taskList.getList().size() - 1));
        System.out.println("Now you have " + taskList.getList().size() + " tasks in the list." );
    }

    public void printDeleteTask(TaskList taskList, Task task) {
        System.out.println("Noted. I've deleted this task:\n" + task);
        System.out.println("Now you have " + (taskList.getList().size()) + " tasks in the list." );
    }

    public void printDoneTask(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void printList(TaskList list) {
        int i = 1;
        if (list.getList().isEmpty()) {
            System.out.println("List is Empty");
        }
        for(Task t : list.getList()) {
            System.out.println(i + "." + t);
            i++;
        }
    }

    public void printFind() {
        System.out.println("Here are the matching tasks in your list:");
    }
    public void showError(String error) {
        System.out.println(error);
    }
}
