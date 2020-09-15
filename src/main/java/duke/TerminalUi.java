package duke;

import java.util.List;
import java.util.Scanner;

public class TerminalUi extends Ui {
    private Scanner sc;

    public TerminalUi() {
        sc = new Scanner(System.in);
    }

    public void greet() {
        printFormattedMessage(GREETING_MESSAGE);
    }

    public String getNextInput() {
        return sc.nextLine();
    }

    /**
     * Prints message in a given format from a variable number of strings.
     *
     * @param response Strings to be printed in a given format.
     */
    public void printFormattedMessage(String... response) {
        System.out.println(LINE);
        for (String string : response) {
            System.out.println(string);
        }
        System.out.println(LINE);
    }

    @Override
    public void processClose() {
        printFormattedMessage(EXIT_MESSAGE);
        sc.close();
    }

    @Override
    public void processError(String errorMessage) {
        printFormattedMessage(errorMessage);
    }

    @Override
    public void listStoredTasks(List<Task> storedTasks) {
        if (storedTasks.isEmpty()) {
            printFormattedMessage("No tasks stored...");
        } else {
            int count = 1;
            String listedTasksString = "";
            for (Task task : storedTasks) {
                listedTasksString += count + ". " + task + "\n";
                count++;
            }
            printFormattedMessage("Quack! Here are the tasks in your list:", listedTasksString);
        }
    }

    @Override
    public void processDoneMessage(Task task) {
        printFormattedMessage("Quack! I have marked this task as done:", task.toString());
    }

    @Override
    public void processAddMessage(Task task, int count) {
        printFormattedMessage("Quack! I have added: ", task.toString(), displayTaskCount(count));
    }

    @Override
    public void processDeleteMessage(Task taskToDelete, int count) {
        printFormattedMessage("Quack! I have deleted this task: ",
                taskToDelete.toString(), displayTaskCount(count));
    }

    /**
     * Returns display message for task count.
     *
     * @param numOfTasks Number of tasks.
     * @return Display message.
     */
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
            printFormattedMessage("No tasks matched...");
        } else {
            int count = 1;
            String resultTasksString = "";
            for (Task task : resultTaskList) {
                resultTasksString += count + ". " + task + "\n";
                count++;
            }
            printFormattedMessage("Quack! Here are the tasks in your list that match:", resultTasksString);
        }
    }
}
