package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {
    private static String horizontalLine = "________________________________________";
    
    public static void showWelcome() {
        String logo = " ____        _" + System.lineSeparator()
                + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "| | | | | | | |/ / _ \\" + System.lineSeparator()
                + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
        String greeting = "Hello human, I'm Duke the supporting chatbot" + System.lineSeparator()
                + "What can I do for you?";
        System.out.println(logo);
        System.out.println(horizontalLine);
        System.out.println(greeting);
    }
    
    public static String requestCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println(horizontalLine);
        System.out.println("Your command:");
        String input = sc.nextLine();
        System.out.println(horizontalLine);
        return input;
    }
    
    public static void showTasks(TaskList tasks) {
        String lazyHumanBash = "You have nothing in your list. Find something to do you human!";
        String showTasksMsg = "Here are the task(s) in your list:";
        if (tasks.size() == 0) {
            System.out.println(lazyHumanBash);
        } else {
            System.out.println(showTasksMsg);
            System.out.println(tasks);
        }
    }
    
    public static void showFileLoadingError() {
        System.out.println("Data cannot be loaded");
    }
    
    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
    
    public static void showGoodbye() {
        System.out.println("Bye human. See you again soon!");
    }
    
    public static void taskReport(TaskList tasks) {
        System.out.println("You have " + tasks.size() + " task(s) in your list");
    }
    
    public static void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Noted. I've deleted this task:");
        System.out.println(task);
        taskReport(tasks);
    }
    
    public static void showMarkAsDoneTask(Task task) {
        System.out.println("Good job bud! I've marked this task as done:");
        System.out.println(task);
    }
    
    public static void showUnrecognizedCommandMessage() {
        System.out.println("I don't understand a single word you say, human!");
        System.out.println("Speak robot language pls");
    }
    
    public static void showAddSuccessful(Task task, TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        taskReport(tasks);
    }
    
    public static void showFindResult(TaskList tasks) {
        System.out.println("Here are the matching task(s) in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(tasks.get(i));
        }
    }
}
