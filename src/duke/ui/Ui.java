package duke.ui;

import duke.storage.TaskList;
import duke.task.Task;
import java.util.Scanner;

public class Ui {
    private static final String CHATBOT = "Bob: ";
    private static final String SKIPLINE = "\n";
    private static final String USER = SKIPLINE + "You: ";
    private static final String DIVIDER = SKIPLINE + CHATBOT;
    
    public static boolean EXIT = false;
    
    private final Scanner scan;
    
    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public String readUserCommand() {
        return this.scan.nextLine();
    }
    
    public void showGreetings() {
        System.out.println(CHATBOT + "Hey there! I'm Bob" + SKIPLINE + "What can I do for you today?");
        System.out.println(USER);
    }
    
    public void showGoodbyeMessage() {
        System.out.println(DIVIDER + "Goodbye! Have a nice day :D");
        this.scan.close();
        EXIT = true;

    }
    
    public void showTaskList(TaskList tasks) {
        System.out.println(DIVIDER);

        if (tasks.totalNumberOfTasks() == 0) {
            System.out.print("List is empty :(");
        } 
        
        if (tasks.totalNumberOfTasks() > 0) {
            System.out.println("Your current tasklist is as follows:");
            System.out.println(tasks);
        }

        System.out.println(USER);
    }
    
    public void loadTaskList(TaskList tasks) {
        System.out.println("Here is your current tasklist:");
        if (tasks.totalNumberOfTasks() == 0) {
            System.out.print("List is empty :(");
            System.out.println(SKIPLINE);
        }

        if (tasks.totalNumberOfTasks() > 0) {
            System.out.println(tasks);
        }
    }
    
    public String showInvalidUserCommand(String userCommand) {
        return "Sorry but I don't understand what '" + userCommand + "' means :(";
    }
    
    public void showAddedNewTaskMessage(Task newTask, TaskList tasks) {
        System.out.println(DIVIDER);
        System.out.println("Noted! I have added the following task to your list:");
        System.out.println(newTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        showSuccessfullySavedMessage();
        System.out.println(USER);

    }

    public void showDeleteTaskMessage(Task deletedTask, TaskList tasks) {
        System.out.println(DIVIDER);
        
        // Bob's response
        System.out.println("Noted! I have deleted this task from your list:");
        System.out.println(deletedTask);
        System.out.println("You now have " + tasks.totalNumberOfTasks() + " task(s) in your list");
        System.out.println(USER);

    }
    
    public void showMarkDoneMessage(Task doneTask) {
        System.out.println(DIVIDER);

        // Bob's response
        System.out.println("Good job completing this task! I've marked this task as done:");
        System.out.println(doneTask);
        System.out.println("Keep up the good work :)");
        System.out.println(USER);

    }
    
    public void showAlreadyMarkDoneMessage(Task doneTask) {
        System.out.println(DIVIDER);
        System.out.println("OOPS. It seems like this task has already been completed:");
        System.out.println(doneTask);
        System.out.println(USER);
    }
    
    public void showErrorMessage(Exception e) {
        System.out.println(DIVIDER);
        System.out.println(e.getMessage());
        System.out.println(USER);
    }
    
    public void showSuccessfullySavedMessage() {
        System.out.println(SKIPLINE + "Successfully saved tasklist to file :)");
    }
}
