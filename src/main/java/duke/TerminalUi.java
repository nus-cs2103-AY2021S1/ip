package duke;

import java.util.List;
import java.util.Scanner;

public class TerminalUi extends Ui {
    private Scanner sc;

    public TerminalUi() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(GREETING_MESSAGE);
    }

    public String getNextInput() {
        return sc.nextLine();
    }

    public void printFormattedMessage(String... response) {
        System.out.println(LINE);
        for (String string : response) {
            System.out.print(string);
        }
        System.out.println("\n" + LINE);
    }

    @Override
    public void processClose() {
        System.out.println(EXIT_MESSAGE);
        sc.close();
    }

    @Override
    public void processError(String errorMessage) {
        System.out.println(LINE);
        System.out.println(errorMessage);
        System.out.println(LINE);
    }

    @Override
    public void listStoredTasks(List<Task> storedTasks) {
        if (storedTasks.isEmpty()) {
            System.out.println(LINE);
            System.out.println("No tasks stored...");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("Quack! Here are the tasks in your list:");
            int count = 1;
            for (Task task : storedTasks) {
                System.out.println(count + ". " + task);
                count++;
            }
            System.out.println(LINE);
        }
    }

    @Override
    public void processDoneMessage(Task task) {
        printFormattedMessage("Quack! I have marked this task as done: \n" + task);
    }

    @Override
    public void processAddMessage(Task task, int count) {
        printFormattedMessage("Quack! I have added: " + task + "\n", displayTaskCount(count));
    }

    @Override
    public void processDeleteMessage(Task taskToDelete, int count) {
        printFormattedMessage("Quack! I have deleted this task: \n" + taskToDelete + "\n" + displayTaskCount(count));
    }

    public String displayTaskCount(int numOfTasks) {
        if (numOfTasks == 1) {
            return "My duck senses tell me you have 1 task in the list.";
        } else {
            return "My duck senses tell me you have " + numOfTasks + " tasks in the list.";
        }
    }

    @Override
    public void processResultTaskList(List<Task> resultTaskList) {
        if (resultTaskList.isEmpty()) {
            System.out.println(LINE);
            System.out.println("No tasks matched...");
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println("Quack! Here are the tasks in your list that match:");
            int count = 1;
            for (Task task : resultTaskList) {
                System.out.println(count + ". " + task);
                count++;
            }
            System.out.println(LINE);
        }
    }
}
