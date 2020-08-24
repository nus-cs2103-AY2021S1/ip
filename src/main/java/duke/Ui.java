package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    
    public Ui() {}
    
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
    
    public void showByeMessage() {
        System.out.println ("Bye!! See you again :)");
    }
    
    public void showAddTaskMessage(Task t, int number) {
        System.out.println ("Got it! I've added this task:");
        System.out.println (t.toString());
        System.out.printf ("Now you have %d tasks in the list \n", number);
    }
    
    public void showRemoveTaskMessage (Task t, int number) {
        System.out.println ("Noted. I've removed this task");
        System.out.println (t.toString());
        System.out.printf ("Now you have %d tasks in the list \n", number);
    }
    
    public void showDoneMessage (Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }
    
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
}
