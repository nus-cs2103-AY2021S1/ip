package duke.ui;

import duke.Duke;
import duke.task.Task;

public class Ui {

    private Duke duke;

    public Ui(Duke duke) {
        this.duke = duke;
    }

    public void greet() {
        System.out.println(UIPrint.logo);

        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Hello! I'm duke.Duke");
        System.out.println("What can I do for you?");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void echo(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(str);
        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void reportCurrentTasks() {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Current tasks:\n");

        for (int i = 0; i < duke.taskList.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + duke.taskList.tasks.get(i));
        }

        if (duke.taskList.tasks.size() == 0) {
            System.out.println("None");
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void reportNewTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Got it. I've added this duke.task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.taskList.tasks.size() + " tasks in the list.");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void reportDoneTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Nice, I've marked this duke.task as done:");
        System.out.println(task);

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void reportDeleteTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Noted. I've removed this duke.task: ");
        System.out.println(task);
        System.out.println("Now you have " + duke.taskList.tasks.size() + " tasks in the list");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public void reportExit() {
        UIPrint.drawLine(UIPrint.star, 50);

        String exitWords = "Bye, hope to see you again soon!";
        System.out.println(exitWords);

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
