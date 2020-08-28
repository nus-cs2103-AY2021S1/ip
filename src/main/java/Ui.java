import java.util.Scanner;

public class Ui {
    private Scanner sc;
    
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    
    public void greet() {
        System.out.println("Hello, I'm Eggy!\n" + "How may I help you?");
    }
    
    public void exit() {
        System.out.println("Bye, see you soon!");
        sc.close();
    }
    
    public String readCommand() {
        String command = sc.nextLine();
        return command;
 
    }
    
    public void showAddition(Task task, TaskList taskList) {
        System.out.println("Added this task to your list:\n" + task);
        showTaskTotal(taskList);
    }
    
    public void showCompletion(Task task) {
        System.out.println("Nice! I've marked this task as done: \n" + task);
    }
    
    public void showDeletion(Task task, TaskList taskList) {
        System.out.println("Noted. Removed task: \n" + task);
        showTaskTotal(taskList);
    }
    
    public void showNoTask() {
        System.out.println("No tasks in your list yet");
    }
    
    public void showLoadingError() {
        System.out.println("Error loading files");
    }
    
    public void showError(String message) {
        System.out.println(message);
    }
    
    public void showTaskTotal(TaskList taskList) {
        int total = taskList.getSize();
        System.out.printf("You now have %d task%s in the list.\n", total, total > 1 ? "s" : "");
    }
}
