import java.util.Scanner;

public class Ui {
    public Scanner sc;
    
    public Ui() {
        sc = new Scanner(System.in);
    }
    
    public void showLine() {
        System.out.println("————————————————————————————————————————————————————————————");
    }
    
    public void opening() {
        this.showLine();
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        this.showLine();
    }
    
    public String userInput() {
        return sc.nextLine();
    }
    
    public void closing() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
        sc.close();
    }    
}
