package duke;

import duke.task.Task;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readInput() {
        return this.sc.nextLine().trim();
    }

    public void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printResponse("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void echo(String response) {
        printResponse(response);
    }

    public void printExit() {
        printResponse("Bye. Hope to see you again soon!");
    }

    public void printList(TaskList taskList) {
        printResponse("Here are the tasks in your list:" + taskList);
    }

    public void printAddTask(Task task, TaskList taskList) {
        printResponse("Got it. I've added this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    public void printDeleteTask(Task task, TaskList taskList) {
        printResponse("Noted. I've removed this task: \n\t" + task + "\n" + getHowManyTasks(taskList));
    }

    public void printDoneTask(Task task) {
        printResponse("Nice! I've marked this task as done:\n\t" + task);
    }

    public String getHowManyTasks(TaskList taskList) {
        int numberOfTasks = taskList.getNumberOfTasks();
        String output = "";
        if (numberOfTasks == 1) {
            output = String.format("Now you have %d task in the list.", numberOfTasks);
        } else {
            output = String.format("Now you have %d tasks in the list.", numberOfTasks);
        }
        return output;
    }

    public void printViewTasks(TaskList taskList, LocalDate localDate) {
        TaskList taskListOnDate = taskList.getTaskListOnDate(localDate);
        printResponse("Here are the tasks on this date:" + taskListOnDate);
    }

    public void showError(Exception e) {
        printResponse(e.getMessage());
    }
}
