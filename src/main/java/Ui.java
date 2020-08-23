import java.util.Scanner;

public class Ui {
    private final String DIVIDER = "---------------------------------------------------------------------------------------------";
    private final String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";
    
    public void showLine() {
        System.out.println("\t" + DIVIDER);
    }
    
    public void showWelcome() {
        System.out.println("\n" + LOGO);
        showLine();
        System.out.println("\t" + "Hello! I'm Duke\n\tWhat can I do for you?");
        showLine();
    }
    
    public void showOutput(String output) {
        showLine();
        System.out.println(output);
        showLine();
        System.out.println();
    }
    
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        
        return sc.nextLine();
    }
}
