package duke;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Ui {
    PrintStream out;
    
    public Ui() {
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            out = new PrintStream(System.out, true);
        }
    }
    
    public void showWelcomeScreen() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        out.println("Hello, my name is\n" + logo);
        out.println("What can I do for you?");
    }
    
    public void showGoodbyeScreen() {
        out.println("See you space cowboy!");
    }
    
    public void printExceptionMessage(Exception e) {
        out.println(e);
    }
    
    public void printAddTaskConfirmation(Task task, TaskList taskList) {
        out.println("Got it. I've added this task: ");
        out.println(task);
        out.println("Now you have " + taskList.numTasks() + " tasks in the list.");
    }
    
    public void printRemoveTaskConfirmation(Task task, TaskList taskList) {
        out.println("Noted. I've removed this task: ");
        out.println(task);
        out.println("Now you have " + taskList.numTasks() + " tasks in the list.");
    }
    
    public void printMarkTaskCompleteConfirmation(Task task) {
        out.println("Nice! I've marked this task as done: ");
        out.println(task);
    }
    
    public void printAllTasks(TaskList taskList) {
        out.println(taskList.getAllTasksAsString());
    }
}
