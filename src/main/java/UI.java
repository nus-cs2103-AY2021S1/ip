import java.util.Scanner;

public class UI {

    private final String LOGO = " ____        _        \n"
                              + "|  _ \\ _   _| | _____ \n"
                              + "| | | | | | | |/ / _ \\\n"
                              + "| |_| | |_| |   <  __/\n"
                              + "|____/ \\__,_|_|\\_\\___|\n";

    private final Scanner in = new Scanner(System.in);
    private final String DIVIDER = "______________________________________________";

    public String prompt() {
        System.out.print(">> ");
        String response = in.nextLine();
        return response;
    }

    public void print(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    public void greet() {
        String greeting = "Hi! I am\n" + LOGO + "\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    public void exit() {
        String goodbye = "Bye! Hope to see you again!";
        print(goodbye);
        System.exit(0);
    }
}
