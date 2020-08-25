package main.java.duke;

import main.java.duke.task.Task;

public class Ui {
    private String horizontalLine = "    ____________________________________________________________";
    private String textIndentation = "     ";

    private String[] greetMessage = new String[] {"Hello! I'm Duke", "What can I do for you?"};
    private String exitMessage = "Bye. Hope to see you again soon!";
    private String addTaskMessage = "Got it. I've added this task:";
    private String completeTaskMessage = "Nice! I've marked this task as done:";
    private String invalidSyntaxMessage = "OOPS!!! I'm sorry, but I don't know what that means :(";
    private String deleteTaskMessage = "Noted. I've removed this task:";
    private String numOfTasksMessage = "Now you have %d tasks in the list";
    private String zeroTasksMessage = "Your task list is currently empty. YAY!!! :D";
    private String listTasksMessage = "Here are the tasks in your list:";
    private String invalidTaskIndexMessage = "Please enter a valid task index";

    private String getNumOfTasksString(TaskList tasks) {
        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 0) {
            return zeroTasksMessage;
        } else {
            return String.format(numOfTasksMessage, numOfTasks);
        }
    }

    /**
     * Prints strings upon completing a task
     * @param task Completed task
     */
    public void printCompleteTask(Task task) {
        String[] strings = new String[] {this.completeTaskMessage, task.toString()};
        this.print(strings);
    }

    /**
     * Prints strings upon creating a new task
     * @param tasks Current task list
     * @param task Created task
     */
    public void printCreateTask(TaskList tasks, Task task) {
        String[] strings = new String[] {this.addTaskMessage, task.toString(), getNumOfTasksString(tasks)};
        this.print(strings);
    }

    /**
     * Prints strings upon deleting a task
     * @param tasks Current task list
     * @param task Deleted task
     */
    public void printDeleteTask(TaskList tasks, Task task) {
        String[] strings = new String[] {this.deleteTaskMessage, task.toString(),getNumOfTasksString(tasks)};
        this.print(strings);
    }

    /**
     * Prints strings upon handling an invalid input
     */
    public void printInvalidInput() {
        String[] strings = new String[] {this.invalidSyntaxMessage};
        this.print(strings);
    }

    /**
     * Prints strings upon receiving an invalid task index
     */
    public void printInvalidTaskIndex() {
        String[] strings = new String[] {this.invalidTaskIndexMessage};
        this.print(strings);
    }

    /**
     * Prints string representation of all tasks
     *
     * @param tasks Current task list
     */
    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.print(new String[] {this.zeroTasksMessage});
        } else {
            String[] strings = new String[tasks.getNumOfTasks() + 1];
            strings[0] = this.listTasksMessage;
            for (int i = 1; i < strings.length; i++) {
                strings[i] = tasks.getTaskAt(i - 1).toString();
            }
            this.print(strings);
        }
    }

    /**
     * Prints strings upon start-up
     */
    void printHello() {
        this.print(this.greetMessage);
    }

    /**
     * Prints strings upon exit
     */
    public void printExit() {
        String[] strings = new String[] {this.exitMessage};
        this.print(strings);
    }

    /**
     * Prints the strings with top and bottom horizontal lines and indentation
     * @param strings Array of strings to be printed
     */
    void print(String[] strings) {
        System.out.println(this.horizontalLine);
        for(String string : strings) {
            System.out.print(this.textIndentation);
            System.out.println(string);
        }
        System.out.println(this.horizontalLine);
    }
}
