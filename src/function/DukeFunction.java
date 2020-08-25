package function;

import ui.UIPrint;
import task.Task;
import command.Command;
import data.DukeData;
import data.DukeCommandSet;
import data.TaskSave;

import exception.IncorrectFormatException;
import exception.InvalidIndexException;
import exception.NoDescriptionException;
import exception.UnknownCommandException;

import java.io.IOException;

public class DukeFunction {

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

        for (int i = 0; i < DukeData.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + DukeData.tasks.get(i));
        }

        if (DukeData.tasks.size() == 0) {
            System.out.println("None");
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportNewTask(Task task) {
        UIPrint.drawLine(UIPrint.star, 50);

        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + DukeData.tasks.size() + " tasks in the list.");

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
        System.out.println("Now you have " + DukeData.tasks.size() + " tasks in the list");

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void reportExit() {
        UIPrint.drawLine(UIPrint.star, 50);

        String exitWords = "Bye, hope to see you again soon!";
        System.out.println(exitWords);

        UIPrint.drawLine(UIPrint.star, 50);
    }

    public static void checkCommand(String str) {
        String[] inputParts = str.split(" ", 2);
        String possibleCommand = inputParts[0];
        String rest = inputParts.length == 2 ? inputParts[1] : "";
        Command command = null;

        try {
            command = DukeCommandSet.getInstance().getCommand(possibleCommand);
        } catch (UnknownCommandException exception) {
            System.out.println(exception.getMessage());
        }

        if (command != null) {
            try {
                command.execute(rest);
            } catch (NoDescriptionException | IncorrectFormatException | InvalidIndexException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public static void loadSavedTasks() throws IOException {
        DukeData.tasks = TaskSave.getInstance().getSavedTasks();
    }

    public static void saveCurrentTasks() throws IOException {
        TaskSave.getInstance().saveTasks(DukeData.tasks);
    }
}
