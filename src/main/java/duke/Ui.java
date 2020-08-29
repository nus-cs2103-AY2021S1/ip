package duke;

import java.util.ArrayList;

public class Ui {
    public void printDivider() {
        System.out.println("          ____________________________________________________________");
    }

    public void printWelcome() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        printOutput("Hi! I'm Duke \nWhat can I do for you?");
        printDivider();
    }
    public void printOutput(String output) {
        String[] splitted = output.split("\n");
        for (String line : splitted) {
            System.out.println("          " + line);
        }
    }

    public void printGoodbye() {
        printOutput("Bye. See you again next time!" );
    }

    public void printAddedTask(Task task, int numberOfTask) {
        printOutput("Got it. I've added this task: ");
        printOutput(task.toString());
        printOutput("Now you have " + numberOfTask + " tasks in the list.");
    }

    public void printTaskList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTask();
        printOutput("Here are the tasks in your list:");
        for (int index = 0; index < tasks.size(); index++) {
            printOutput((index + 1) + ": " + tasks.get(index));
        }
    }

    public void printCompleteTask(Task task) {
        printOutput("Nice! I've marked this task as done:");
        printOutput(task.toString());
    }

    public void printDeleteTask(Task task, int numberOfTask) {
        printOutput("Noted. I've removed this task:");
        printOutput(task.toString());
        printOutput("Now you have " + numberOfTask + " tasks in the list.");

    }

    public void printDeleteAllTasks() {
        printOutput("Noted. I've removed all tasks in the list.");
        printOutput("Now you have no task in the list.");
    }
}
