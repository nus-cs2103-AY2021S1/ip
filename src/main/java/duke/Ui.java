package duke;

import duke.task.TaskList;
import duke.task.Task;

import java.util.Scanner;

public class Ui {
    public Ui() {}

    private enum Speech {
        GREETING("My name? You don't need to know that. Stop bothering me already... "),
        BYE("Finally... don't come back if you can possibly help it, please."),
        NEXTCOMMAND("What do you want now?"),
        LIST("\nI wouldn't bother doing them if I were you."),
        DONE("Oh goody... You actually accomplished something!!\n  "),
        ADD("You're making me feel tired... But if you insist, I've added this:\n  "),
        DELETE("Oh, getting lazy are we? I approve. I've removed this:\n  "),
        UPDATEDTASK("Donezorimasu. Your task is now:\n  "),
        LOADINGERROR("Something went wrong when loading the data file. "
                + "\nGuess you'll be starting from zero.");

        private final String line;

        private Speech(String line) {
            this.line = line;
        }
    }

    public static String showWelcome() {
        return Speech.GREETING.line;
    }

    public String showGoodbye() {
        return Speech.BYE.line;
    }

    public String showDividerLine() {
        return "------------------------------";
    }

    public String showAddTask(Task t) {
        return (Speech.ADD.line + "\n" + t);
    }

    public String showDoneTask(Task t) {
        return (Speech.DONE.line + t);
    }

    public String showDeleteTask(Task t) {
        return (Speech.DELETE.line + t);
    }

    public String showUpdateTask(Task t) {
        return (Speech.UPDATEDTASK.line + t);
    }

    public String showNumberOfTasksLeft(TaskList taskList) {
        int length = taskList.getSize();
        return ("\n\nYou now have " + length
                + (length == 1 ? " thing" : " things") + " in your list");
    }

    public String showLoadingError() {
        return Speech.LOADINGERROR.line;
    }

    public String showError(String errorMsg) {
        return errorMsg;
    }

    public String showAllTasks(TaskList taskList) {
        String s = "";
        for (int i = 0; i < taskList.getSize(); i++) {
            s += (i + 1 + ". " + taskList.getTask(i) + "\n");
        }
        s += Speech.LIST.line;
        return s;
    }

    // TODO: CHANGE THIS
//    public String readUpdateDesc() {
//        System.out.println(Speech.UPDATEINPUT.line);
//        Scanner sc = new Scanner(System.in);
//        String newDesc = sc.nextLine();
////        sc.close();
//        return newDesc;
//    }
}
