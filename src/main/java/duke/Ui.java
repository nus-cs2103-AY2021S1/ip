package duke;

import main.java.duke.task.TaskList;
import main.java.duke.task.Task;

import java.util.Scanner;

public class Ui {
    public Ui() {}

    private enum Speech {
        GREETING("My name? You don't need to know that. Stop bothering me already... "),
        BYE("Finally... don't come back if you can possibly help it, please."),
        NEXTCOMMAND("What do you want now?"),
        LIST("I wouldn't bother doing them if I were you."),
        DONE("Oh goody... You actually accomplished something!!\n  "),
        ADD("You're making me feel tired... But if you insist:\n  "),
        DELETE("Oh, getting lazy are we? I approve. I've removed this:\n  "),
        UPDATEINPUT("You're so troublesome... What updated duke.task description do you want?"),
        UPDATEDTASK("Donezorimasu. Your duke.task is now:\n  "),
        LOADINGERROR("Something went wrong when loading the data file. "
                + "\nGuess you'll be starting from zero.");

        private final String line;

        private Speech(String line) {
            this.line = line;
        }
    }

    public void showWelcome() {
        System.out.println(Speech.GREETING.line);
    }

    public void showGoodbye() {
        System.out.println(Speech.BYE.line);
    }

    public void showDividerLine() {
        System.out.println("------------------------------");
    }

    public void showAddTask(Task t) {
        System.out.println(Speech.ADD.line + "\n" + t);
    }

    public void showDoneTask(Task t) {
        System.out.println(Speech.DONE.line + t);
    }

    public void showDeleteTask(Task t) {
//        String s = Speech.DELETE.line + t;
//        System.out.println(s);
//        return s;
        System.out.println(Speech.DELETE.line + t);
    }

    public void showUpdateTask(Task t) {
        System.out.println(Speech.UPDATEDTASK.line + t);
    }

    public void showNumberOfTasksLeft(TaskList taskList) {
        int length = taskList.getSize();
        System.out.println("\nYou now have " + length
                + (length == 1 ? " thing" : " things") + " in your list");
    }

    public void showLoadingError() {
        System.out.println(Speech.LOADINGERROR.line);
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showAllTasks(TaskList taskList) {
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println(i + 1 + ". " + taskList.getTask(i));
        }
        System.out.println(Speech.LIST.line);
    }

    public String readCommand() {
        System.out.println(Speech.NEXTCOMMAND.line);
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
//        sc.close();
        return command;
    }

    public String readUpdateDesc() {
        System.out.println(Speech.UPDATEINPUT.line);
        Scanner sc = new Scanner(System.in);
        String newDesc = sc.nextLine();
//        sc.close();
        return newDesc;
    }
}
