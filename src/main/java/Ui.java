import java.util.ArrayList;
import java.util.Scanner;

//deals with interactions with the user
public class Ui {
    private Scanner scanner;
    
    public Ui () {
        scanner = new Scanner(System.in);
    }
    
    public void printWelcome() {
        String logo =
                " ____                    _\n"
                        + "|  _ \\                  | |\n"
                        + "| |_| |___  _ _  __  ___| | ___  _\n"
                        + "| ___/  _ \\| | |/  |/ _   |/ _ \\| |\n"
                        + "| |  | |_|   |  _  | |_|  | |_|   |\n"
                        + "|_|  \\____,__|_| |_|\\___,_|\\___,__|" + " bot\n\n";

        System.out.println(logo + "Hello! I'm PandaBot.\n" + "What can I do for you?\n");
    }
    
    public void printLine() {
        System.out.println(" ___________________________________________________");
    }
    
    public String readCmd() {
        return scanner.nextLine();
    }
    
    public boolean hasNextCmd() {
        return scanner.hasNextLine();
    }

    public void showError(String message) {
        System.out.println(message);
    }
    
    // print on bye command
    public void printOnExit() {
        System.out.println("Bye! Remember to finish the rest of your work! See you soon~");
        scanner.close();
    }
    
    public void printOnList(TaskList tasks) {
        int len = tasks.size();
        if (len == 0) {
            System.out.println("WOOTS! You don't have any tasks to do at the moment.");
        } else {
            System.out.println("These are the task(s) you have: ");
            int i = 0;
            for (Task t : tasks.getTaskList()) {
                if (t != null) {
                    System.out.println((i + 1) + ". " + t.toString());
                    i++;
                } else {
                    break;
                }
            }
        }
    }
    
    public void printOnDone(Task task) {
        System.out.println("Great! I've marked this task as done:");
        System.out.println(task);
    }
    
    public void printOnDelete(Task task, TaskList tasks) {
        System.out.println("Will do! I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in this list.");
    }
    
    public void printOnAddTask(Task task, TaskList tasks) {
        System.out.println("Noted! I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in this list.");
    }
    
    public void printOnFind(TaskList tasks) {
        System.out.println("Here are the matching tasks: ");
        int i = 0;
        for (Task t : tasks.getTaskList()) {
            if (t != null) {
                System.out.println((i + 1) + ". " + t.toString());
                i++;
            } else {
                break;
            }
        }   
    }
    
}
