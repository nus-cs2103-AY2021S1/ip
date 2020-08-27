package duke;

import duke.task.Task;
import java.util.List;

public class Ui {
    public static String line = "______________________________________________________";
    public static String lineIndent = "    ";
    public static String listIndent = "       ";
    public static String textIndent = "     ";

    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(String.format("Hello from\n%s\nWhat can I do for you?",
                logo));
    }

    public void printMessage(String message) {
        System.out.println(String.format("%s%s\n%s%s\n%s%s", lineIndent, line,
                textIndent, message, lineIndent, line));
    }

    public void listTasks(List<Task> taskList) {
        String output = "Here are the tasks in your list:\n";
        int taskLen = taskList.size();
        for (int i = 0; i < taskLen; i++) {
            output += String.format("%s%d. %s", textIndent, i + 1, taskList.get(i));
            if (i != taskLen - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }

    public void completeSuccess(Task task) {
        printMessage(String.format("Nice! I've marked this task as done:\n%s%s",
                listIndent, task));
    }

    public void deleteSuccess(Task task, int taskCount) {
        String message = String.format("Noted. I've removed this task:\n%s%s\n%sNow you have %d %s in the list.",
                listIndent, task, textIndent, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    public void addSuccess(Task task, int taskCount) {
        String message = String.format("Got it. I have added this task:\n%s%s\n%sNow you have %d %s in the list.",
                listIndent, task, textIndent, taskCount, taskCount > 1 ? "tasks" : "task");
        printMessage(message);
    }

    public void listTasksWithDate(List<Task> taskList, String dateString) {
        String output = String.format("Here are the tasks with the date %s:\n", dateString);
        int counter = 1;
        for (Task task : taskList) {
            output += String.format("%s%d. %s\n", textIndent, counter, task);
            counter++;
        }
        printMessage(output);
    }

    public void listTasksWithWord(List<Task> taskList) {
        String output = "Here are the matching tasks in your list:\n";
        int taskSize = taskList.size();
        for (int i = 0; i < taskSize; i++) {
            output += String.format("%s%d. %s", textIndent, i+1, taskList.get(i));
            if (i != taskSize - 1) {
                output += "\n";
            }
        }
        printMessage(output);
    }
}
