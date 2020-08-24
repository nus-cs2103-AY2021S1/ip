import java.util.Scanner;

public class Ui {
    Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public String readCommand() {
        return sc.nextLine();
    }
    
    public void showLine() {
        System.out.println("------------------------------------------------------");
    }
    
    public void showWelcome() {
        System.out.println("Serina here, how can I assist you?");
        this.showLine();
    }
    
    public void showTask(Task task, int taskNum) {
        System.out.println(taskNum + ". " + task);
    }
    
    public void showAddTask(Task task, int taskNum) {
        System.out.println("The following task has been added: ");
        this.showTask(task, taskNum);
        this.showLine();
    }
    
    public void showDeleteTask(Task task, int taskNum) {
        System.out.println("The following task has been deleted: ");
        this.showTask(task, taskNum);
        this.showLine();
    }
    
    public void showDoneTask(Task task, int taskNum) {
        System.out.println("The following task has been marked as done: ");
        this.showTask(task, taskNum);
        this.showLine();
    }
    
    public void showGoodbye() {
        System.out.println("Alright, call me again if you need me.");
    }
    
    public void showError(String err) {
        System.out.println(err);
        this.showLine();
    }
}
