package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class Ui {
    public static String greetings = "Hello! I'm Mr. Duke, your personal assistant\n     What can I do for you? : )";
    public static String farewell = "Bye. Hope to see you again soon!";
    public static String doneAlert = "Nice! I've marked this task as done:";
    public static String addTaskFrontAlert = "Got it. I've added this task for you:";
    public static String addTaskTailAlert = "Now you have %d tasks in the list.";
    public static String deleteTaskFrondAlert = "Noted. I've removed this task:";
    public static String findTaskFrontAlert = "Here are the matching tasks in your list:";

    public Ui() {

    }

    public void greetings() {
        printAnswer("", greetings, "");
    }

    public void addTaskAlert(TaskList result) {
        printAnswer(addTaskFrontAlert, "   " + result.get(result.getSize() - 1).toString(), String.format(addTaskTailAlert, result.getSize()));
    }

    public void doneAlert(Task tempDone) {
        printAnswer(doneAlert, "   " + tempDone.toString(), "");
    }

    public void deleteTaskAlert(Task tempDelete, TaskList result) {
        printAnswer(deleteTaskFrondAlert, "   " + tempDelete.toString(), String.format(addTaskTailAlert, result.getSize()));
    }

    public void showList(TaskList result) {
        printList(":) Here are all the tasks in your list:", result, "");
    }

    public void farewell() {
        printAnswer("", farewell, "");
    }

    public void findTaskAlert(TaskList result) {
        printList(findTaskFrontAlert, result, "");
    }
    public static void printAnswer(String FrontGuidance, String answer, String TailGuidance) {
        String line = "___________________________________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }
        System.out.println(bigSpace + answer);
        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }
        System.out.println(smallSpace + line + "\n");
    }

    public static void printList(String FrontGuidance, TaskList result, String TailGuidance) {
        String line = "___________________________________________________________________________________";
        String smallSpace = "    ";
        String bigSpace = "     ";

        System.out.println(smallSpace + line);
        if (FrontGuidance.length() != 0) {
            System.out.println(bigSpace + FrontGuidance);
        }

        for (int i = 0; i < result.getSize(); i++) {
            System.out.println(bigSpace + (i + 1) + ". " + result.get(i));
        }

        if (TailGuidance.length() != 0) {
            System.out.println(bigSpace + TailGuidance);
        }

        System.out.println(smallSpace + line + "\n");
    }

    public void showError(Exception e) {
        System.out.println(e.toString());
    }

}
