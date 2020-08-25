package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    
    public Ui() {}

    /**
     * Shows message upon starting Duke
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println ("Hello! I'm Duke  ^_^");
        System.out.println ("What can I do for you??");
    }

    /**
     * Shows message when user enters "bye"
     */
    public void showByeMessage() {
        System.out.println ("Bye!! See you again :)");
    }

    /**
     * Shows message when a task is added to the task list
     * 
     * @param t task added to the list
     * @param number number of tasks in the list
     */
    public void showAddTaskMessage(Task t, int number) {
        System.out.println ("Got it! I've added this task:");
        System.out.println (t.toString());
        System.out.printf ("Now you have %d tasks in the list \n", number);
    }

    /**
     * Shows message when a task is removed from the task list
     * 
     * @param t task removed from list
     * @param number number of tasks in the list
     */
    public void showRemoveTaskMessage (Task t, int number) {
        System.out.println ("Noted. I've removed this task");
        System.out.println (t.toString());
        System.out.printf ("Now you have %d tasks in the list \n", number);
    }

    /**
     * Shows message when a task is marked as done
     * 
     * @param t task that is marked done
     */
    public void showDoneMessage (Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Reads the user input line by line
     * 
     * @return user input
     */
    public String readUserInput() {
        Scanner sc = new Scanner (System.in);
        return sc.nextLine();
    }
    
    public void showList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf ("%d. %s \n", i + 1, list.get(i));
        }
    }
    
    
    public void showLoadingError() {
        System.out.println ("Sorry, I could not retrieve your previous tasks \n");
    }

    /**
     * Shows message if user did not specify description of a task
     */
    public void showNoTaskInputException() {
        System.out.println ("Please specify a task description");
    }

    /**
     * Shows message if user did not specify time of a deadline or event
     */
    public void showNoTimeInputException() {
        System.out.println ("Please specify a time");
    }

    /**
     * Shows message if user tries to retrieve a task that does not exist
     */
    public void showNoTaskExistException() {
        System.out.println ("Task does not exist");
    }
    
    public void showDoNotUnderstandMesssage() {
        System.out.println ("Sorry >_< I don't know what you mean...");
    }
}
