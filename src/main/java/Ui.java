import java.io.IOException;
import java.util.Scanner;

public class Ui {
    
    private final Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void showLoadingError() {
        System.out.println("ERROR: file does not exist.");
    }
    
    public void showError(String msg) {
        System.out.println(msg);
    }
    
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
        System.out.println("Hello from\n" + logo + "\n" + greeting);
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return sc.nextLine();
    }
    
    public String showLine() {
        return "    ____________________________________________________________\n";
    }
    
    public void showDeletedTask(Task task, int length) {
        System.out.println("Noted. I've removed this task:\n" + task + "\n" 
                + "Now you have " + length + " tasks in the list.\n");
    }
    
    public void showAddedTask(Task task, int length) {
        length++;
        System.out.println("Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + length + " tasks in the list.\n");
    }
    
    public void showDone(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task + "\n");
    }
    
    public void showList(TaskList taskList) {
        for (int i = 0; i < taskList.taskListLength(); i++) {
            if (i == 0) {
                System.out.println("Here are the tasks in your list:\n");
            }
            Task task = taskList.getTaskList().get(i);
            System.out.println((i + 1) + ". " + task + "\n");
        }
    } 
}
