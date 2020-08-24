package duke;

import duke.task.NumberedTask;

import java.util.Scanner;
import java.util.List;

public class Ui {

    private final String LINE_SEPARATOR = "***********************";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void printMessage(String msg) {
        System.out.println(LINE_SEPARATOR);
        System.out.println(msg);
        System.out.println(LINE_SEPARATOR);
    }

    public void greet() {
        printMessage("Hi! I'm Duke :-) What can I do for you?");
    }

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public String foundTasksToString(List<NumberedTask> taskList) {
        StringBuilder tasks = new StringBuilder("Here are the matching tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(taskList.get(i).toString());
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        return tasks.toString();
    }

    public void stopReading() {
        scanner.close();
    }
}
