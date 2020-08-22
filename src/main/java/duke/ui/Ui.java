package duke.ui;
import duke.task.Task;
import duke.tool.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printLog(String s) {
        System.out.println(formatOut(s));
    }

    public String formatOut(String s) {
        return String.format("  %s\n    %s\n  %s\n",line(),s,line());
    }

    private String line() {
        return "-------------------------------------";
    }

    public void showWelcomeMessage() {
        System.out.println(formatOut("Hello, I am duke.Duke !\n\t What can I do for you ?"));
    }

    public void showGoodbyeMessage() {
        System.out.println(formatOut("Bye ! Hope to see you again soon."));
    }

    public  String doneGreetMessage() {
        return "Nice! I've marked this task as done: \n";
    }

    public void showAddedNotification(Task newTask, TaskList taskList) {
        String out = "Got it. I've added this task: " + "\n\t\t" +
                newTask.toString() + "\n\t" +
                String.format("Now you have %d tasks in the list.\n", taskList.getSize());
        System.out.print(this.formatOut(out));
    }

    public void showDeletionNotification(TaskList taskList, Task deletedTask) {

        String out = "Noted. I've removed this task: " + "\n\t\t" +
                deletedTask.toString() + "\n\t" +
                String.format("Now you have %d tasks in the list.\n", taskList.getSize());
        System.out.print(formatOut(out));
    }

    public void showTaskList (TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list: \n\t");

        if (taskList.isEmpty()) {
            System.out.print(formatOut("You haven't added any task here !"));
            return ;
        }

        for(Task task:taskList) {
            builder.append(taskList.indexOf(task) + 1).append(". ")
                    .append(task.toString()).append("\n").append("\t");
        }

        System.out.print(formatOut(builder.toString()));
    }

    public String readCommand() {
        Scanner sc= new Scanner(System.in);
        return sc.nextLine();
    }

    public void showDoneGreet(Task task) {
        System.out.print(formatOut(doneGreetMessage() + "\n\t" + task.toString()));
    }
}
