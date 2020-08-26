package ui;

import data.DukeTaskList;
import task.Task;

public class Ui {

    public static void greet() {
        System.out.println(UIPrint.logo);

        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void echo(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(str);
        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportCurrentTasks() {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Current tasks:\n");

        for (int i = 0; i < DukeTaskList.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + DukeTaskList.tasks.get(i));
        }

        if (DukeTaskList.tasks.size() == 0) {
            System.out.println("None");
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportNewTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + DukeTaskList.tasks.size() + " tasks in the list.");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportDoneTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Nice, I've marked this task as done:");
        System.out.println(task);

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportDeleteTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Noted. I've removed this task: ");
        System.out.println(task);
        System.out.println("Now you have " + DukeTaskList.tasks.size() + " tasks in the list");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportExit() {
        UIPrint.drawLine(UIPrint.star, 50);

        String exitWords = "Bye, hope to see you again soon!";
        System.out.println(exitWords);

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
