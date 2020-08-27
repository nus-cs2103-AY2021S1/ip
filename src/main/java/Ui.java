import java.util.Scanner;

public class Ui {
    public Scanner sc;

    /**
     * Constructs new Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the divider line.
     */
    public void showLine() {
        System.out.println("————————————————————————————————————————————————————————————");
    }

    /**
     * Prints the opening lines in Duke.
     */
    public void opening() {
        this.showLine();
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?");
        this.showLine();
    }

    /**
     * Returns a prompt for user input.
     * 
     * @return Scanner prompt.
     */
    public String userInput() {
        return sc.nextLine();
    }

    /**
     * Prints the closing lines in Duke.
     */
    public void closing() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
        sc.close();
    }    
}
