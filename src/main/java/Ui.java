import java.io.IOException;
import java.util.Scanner;

public class Ui {
    
    private final Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public String showLoadingError() {
        return "ERROR: file does not exist.";
    }
    
    public String showError(String msg) {
        return msg;
    }
    
    public String showWelcome() {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
//        System.out.println("Hello from\n" + logo + "\n" + greeting);
        return "Welcome!";
    }

    public String showBye() {
        return "Bye. Hope to see you again soon!";
    }

    public String readCommand() {
        return sc.nextLine();
    }
    
    public String showLine() {
        return "    ____________________________________________________________\n";
    }
    
    public String showDeletedTask(Task task, int length) {
        return "Noted. I've removed this task:\n" + task + "\n" 
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showAddedTask(Task task, int length) {
        length++;
        return "Got it. I've added this task:\n" + task + "\n"
                + "Now you have " + length + " tasks in the list.\n";
    }
    
    public String showDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task + "\n";
    }
    
    public String showList(TaskList taskList) {
        for (int i = 0; i < taskList.taskListLength(); i++) {
            if (i == 0) {
                return "Here are the tasks in your list:\n";
            }
            Task task = taskList.getTaskList().get(i);
            return (i + 1) + ". " + task + "\n";
        }
        return "";
    } 
}
